package com.daishaowen.test.poihebing;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalaryPoi {
    @SuppressWarnings("deprecation")
    public String getToExcel() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        // 返回表单结果集
        List list = new ArrayList();
        JSONObject object1 = new JSONObject();
        object1.put("departmentName","生产部");
        object1.put("code","123");
        object1.put("name","张三");
        object1.put("bankNum","001");



        //初始化POI
        //创建工作簿---->XSSF代表10版的Excel(HSSF是07版前的Excel)
        HSSFWorkbook wb = new  HSSFWorkbook();

        //工作表
        HSSFSheet sheet = wb.createSheet("淞幸科技");


        //修饰风格2
        HSSFCellStyle cellStyle2 = wb.createCellStyle();//生成表头格样式
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中


//        HSSFRow header=sheet.createRow(1);
        HSSFRow header=sheet.createRow(0);
        //创建单元格，0代表第一行第一列
        String[] heards ={"序号","部门名称","工号","姓名","银行卡号","定级工资","岗位工资"
                ,"实发工资总额"};
        for(int i =0;i<heards.length;i++){
            HSSFCell hssfCell1=header.createCell(i);
            hssfCell1.setCellStyle(cellStyle2);
            hssfCell1.setCellValue(heards[i]);
        }
        //设置列的宽度
//        getPhysicalNumberOfCells()代表这行有多少包含数据的列
        for(int i=0;i<header.getPhysicalNumberOfCells();i++){
            //POI设置列宽度时比较特殊，它的基本单位是1/255个字符大小，
            //因此我们要想让列能够盛的下20个字符的话，就需要用255*20
            sheet.setColumnWidth(i, 255*15);
        }
        //设置行高，行高的单位就是像素，因此30就是30像素的意思
        header.setHeightInPoints(30);
        int k =2;
//
        for(int i=0;i<list.size();i++){
//            //创建多行数据
            HSSFRow headers=sheet.createRow(k++);
//            //拿出所有查询出的一条list(list(Map<>))信息
            Map<String, Object> map = (Map<String, Object>) list.get(i);
            System.out.println(map);

            String[] context ={"","departmentName","code","name","bankNum","salary","workSalary","actuallSalary"};
            for(int j=0;j<context.length;j++){
                if(j==0){
                    HSSFCell hssfCell2=headers.createCell(j);
                    hssfCell2.setCellStyle(cellStyle2);
                    hssfCell2.setCellValue(i+1);
                    continue;
                }
                String fieldName = context[j];
                Object obj = list.get(i);
                Class tCls = obj.getClass();
                Object value;
                if(obj instanceof JSONObject){
                    String getMethodName = "get";
                    Method getMethod = tCls.getMethod(getMethodName, Object.class);
                    value = getMethod.invoke(obj, fieldName);
                }else {
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    value = getMethod.invoke(obj, new Class[]{});
                }
                HSSFCell hssfCell2=headers.createCell(j);
                hssfCell2.setCellStyle(cellStyle2);
                String textValue = null;
                if(value == null) {
                    textValue = "";
                } else{
                    //其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                }
                try{
                    hssfCell2.setCellValue(Double.parseDouble(textValue));
                }catch (Exception e){
                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    hssfCell2.setCellValue(richString);
                }

            }
        }

        // 合并相同列中的数据
        // 处理相同的数据合并单元格
        if(sheet.getLastRowNum()>2){//获取最后一行行标，比行数小  满足合并的条件，2是数据行的开始，0 1 行为表头
            HSSFRow row_1 = sheet.getRow(2);
            HSSFCell cell_1 = row_1.getCell(1);

            String departname = cell_1.getStringCellValue();

            for(int i=3;i<=sheet.getLastRowNum();i++){
                //除第一个外，循环将内容相同的单元格设为""，这里体现出为什么原数据要有序！
                HSSFRow rows = sheet.getRow(i);
                //此处表示对单元格进行内容相同合并处理，我这里获取的是每行的第2列进行对比，要多列对比的，这里自行增加
                HSSFCell cells_1 = rows.getCell(1);
                //这里值相同则设置位空，方便之后的合并
                if(departname.equals(cells_1.getStringCellValue())){
                    cells_1.setCellValue("");
                }else{
                    departname = cells_1.getStringCellValue();
                }
            }
        }
        int sk;
        if(sheet.getLastRowNum()>2){//将为空的单元格与之前不为空的合并
            sk=2;
            for(int i=3;i<=sheet.getLastRowNum();i++){
                HSSFRow rows = sheet.getRow(i);
                HSSFCell cell_0 = rows.getCell(1);
                if(cell_0.getStringCellValue() == ""){//如果为空但还没对比到最后一行，继续循环
                    if(i==sheet.getLastRowNum()){
                        sheet.addMergedRegion(new CellRangeAddress(sk, i, 1, 1));//如果已经对比到最后一行，开始合并
                    }
                }else{
                    if(sk != i-1){//不为空且i-1不为sk则合并
                        sheet.addMergedRegion(new CellRangeAddress(sk, i-1, 1, 1));//起始行号，终止行号， 起始列号，终止列号
                    }
                    sk =i;
                }
            }
        }
        //上面设置好了内容，我们当然是要输出到某个文件的，输出就需要有输出流
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("d:/2010.xls");
            //向指定文件写入内容
            wb.write(fos);
            fos.close();
            return "导出Excel已完成！";

        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        return "导出Excel失败！";
    }
}
