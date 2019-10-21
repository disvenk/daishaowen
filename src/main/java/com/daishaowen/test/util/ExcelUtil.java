package com.daishaowen.test.util;

import com.alibaba.fastjson.JSONObject;
import com.daishaowen.test.canjiacore.util.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil<T> {
    /**
     * 导出Excel的方法
     * @param title excel中的sheet名称
     * @param headers 表头
     * @param result 结果集
     * @param out 输出流
     * @param pattern 时间格式
     * @throws Exception
     */
	public void ExportExcel(String fileName, HttpServletRequest request, HttpServletResponse response, String[] headers , String[] columns, Collection<T> result, Map<String, String>map) throws Exception {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet("sheet1");

        // 声明一个画图的顶级管理器
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        // 标题样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 标题字体
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("微软雅黑");
        titleFont.setColor(IndexedColors.BLACK.index);
        titleFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        titleFont.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);
        // 正文样式
        XSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.cloneStyleFrom(titleStyle);
        // 正文字体
        XSSFFont bodyFont = workbook.createFont();
        bodyFont.setFontName("宋体");
        bodyFont.setColor(IndexedColors.BLACK.index);
        bodyFont.setFontHeightInPoints((short) 12);
        bodyStyle.setFont(bodyFont);
        XSSFRow row = null;
        XSSFCell cell = null;


        // 产生表格标题行
            row = sheet.createRow(0);
            // 设置行高
            row.setHeightInPoints(25f);
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell2 = row.createCell(i);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell2.setCellValue(text);
                cell2.setCellStyle(titleStyle);
               // sheet.autoSizeColumn((short) i); //调整每一列宽度
                //sheet.setColumnWidth(i, (20* 256));

            }


        // 遍历集合数据，产生数据行
        if(result != null){
            int index = 1;
            for(T t:result){
            //  Field[] fields = t.getClass().getDeclaredFields();
                row = sheet.createRow(index);
                row.setHeightInPoints(20f);
                index++;
                for(int i = 0; i < columns.length; i++) {
                    XSSFCell cell3 = row.createCell(i);
                    String fieldName = columns[i];
                    Class tCls = t.getClass();
                    Object value;
                    if(t instanceof JSONObject){
                        String getMethodName = "get";
                        Method getMethod = tCls.getMethod(getMethodName, Object.class);
                         value = getMethod.invoke(t, fieldName);
                    }else {
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        value = getMethod.invoke(t, new Class[]{});
                    }

                    String textValue = null;
                    if(value == null) {
                        textValue = "";
                    } else{
                        //其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                     }
                    if(textValue!= null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            //是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                            XSSFCellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
                            cell.setCellStyle(cellStyle);
                        } else {
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);
                            cell3.setCellValue(richString);
                        }

                    }
                }
            }
        }

        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < headers.length; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(colNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        int length = 0;
                        try {
                            length = currentCell.getStringCellValue().getBytes().length;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }

            }
            if(colNum == 0){
                sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
            }else{
                sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
            }
        }
//        for (int i = 0; i < headers.length; i++) {
//            // 调整每一列宽度
//            sheet.autoSizeColumn((short) i);
//            // 解决自动设置列宽中文失效的问题
//            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
//        }

        response.setContentType(
                "application/vnd.ms-excel");

        String agent = request
                .getHeader("user-agent");//获取所使用的浏览器类型
        fileName = FileUtils.encodeDownloadFilename(fileName, agent);//按照指定的浏览器进行编码
        response.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);//设置下载的文件名称

        ServletOutputStream outputStream =response
                .getOutputStream();//获取输入流
        workbook.write(outputStream);//写入导出

        // 关闭
        outputStream.close();
//
    }

}
