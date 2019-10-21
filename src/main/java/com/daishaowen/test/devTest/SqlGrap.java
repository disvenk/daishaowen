package com.daishaowen.test.devTest;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqlGrap extends JFrame implements ActionListener {
    JButton btn = null;
    JButton btn1 = null;
    JButton btn2 = null;

    JTextField textField = null;
    JTextField textField1 = null;
    JTextField textField2= null;
    JPanel jpCenter = new JPanel();
    JTextArea  text = new JTextArea ();

    JComboBox xiaLa;

    public static void main(String[] args) {
      new SqlGrap();
    }

    public SqlGrap()
    {
        this.setTitle("tomcat和nginx日志解析器");
        FlowLayout layout = new FlowLayout();// 布局
        JLabel label = new JLabel("选择文件：");// 标签
        JLabel label1 = new JLabel("保存路径：");// 标签
        JLabel label5 = new JLabel("过滤秒数：");// 标签
        JLabel label2 = new JLabel("                    ");// 标签
        JLabel label3 = new JLabel("                               ");// 标签
        JLabel label4 = new JLabel("                    ");// 标签
        JLabel label6 = new JLabel("                                                              ");// 标签
        JLabel label7 = new JLabel("日志类型：");// 标签
        textField = new JTextField(30);// 文本域
        textField1 = new JTextField(30);// 文本域
        textField2 = new JTextField(5);// 文本域

        xiaLa = new JComboBox();
        xiaLa.addItem("tomcat");
        xiaLa.addItem("ngnix");

        btn = new JButton("浏览1");// 钮1
        btn1 = new JButton("浏览2");// 钮1
        btn2 = new JButton("开始解析");// 钮1

        text.setFont(new Font("黑体",0,20));
        //颜色
        text.setForeground(Color.RED);
        //文本居中
        //StyledDocument doc = text.getStyledDocument();
        //SimpleAttributeSet center = new SimpleAttributeSet();
        //StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        //doc.setParagraphAttributes(0, doc.getLength(), center, false);
        //禁止操作文本域
        text.setEditable(false);
        //背景透明
        text.setOpaque(false);


        jpCenter.setBounds(0,80,600,400-200);
        jpCenter.setLayout(new BorderLayout());
        jpCenter.add(text,BorderLayout.CENTER);


        //字体

        // 设置布局
        layout.setAlignment(FlowLayout.LEFT);// 左对齐
        this.setLayout(layout);
        this.setBounds(400, 200, 600, 300);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn.addActionListener(this);
        this.add(label);
        this.add(textField);
        this.add(btn);
        this.add(label2);


        btn1.addActionListener(this);
        this.add(label1);
        this.add(textField1);
        this.add(btn1);
        this.add(label3);

        this.add(label5);
        this.add(textField2);
        this.add(label7);
        this.add(xiaLa);

        btn2.addActionListener(this);
        this.add(btn2);
        this.add(label6);

        this.add(jpCenter);


    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        String actionCommand = e.getActionCommand();
        if(actionCommand.contains("浏览")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showDialog(new JLabel(), "选择");
            File file = chooser.getSelectedFile();
            if(actionCommand.equals("浏览1"))
                textField.setText(file.getAbsoluteFile().toString());
            if(actionCommand.equals("浏览2"))
                textField1.setText(file.getAbsoluteFile().toString());
        }else if(actionCommand.contains("开始解析")) {

            if (textField == null || "".equals(textField.getText()))
                text.setText("请选择要解析的文件");
            else if (textField1 == null || "".equals(textField1.getText()))
                text.setText("请选择要保存的路径");
            else if ("".equals(textField2.getText().trim()))
                text.setText("请选择要过滤的秒数");
            else{
                if(xiaLa.getSelectedItem().toString().equals("tomcat")) {
                    try {
                        tomcat(textField.getText(), textField1.getText(), textField2.getText());
                        text.setText("导出成功!");
                    } catch (Exception e1) {
                        text.setText("导出错误，请检查传入参数");
                    }
                }else if(xiaLa.getSelectedItem().toString().equals("ngnix")){
                    try {
                        ngnixToMysql(textField.getText(), textField1.getText(), textField2.getText());
                        text.setText("导出成功!");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        text.setText("导出错误，请检查传入参数");
                    }
                }

            }
        }

    }

    public void tomcat(String fileName,String savePath,String seconds) throws Exception{
        Integer maxSeconds=Integer.parseInt(seconds)*1000;
        //创建新的Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet");
        // 标题样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 标题字体
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("微软雅黑");
        titleFont.setColor(HSSFColor.BLACK.index);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        titleFont.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);

        // 正文样式
        XSSFCellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.cloneStyleFrom(titleStyle);
        // 正文字体
        XSSFFont bodyFont = workbook.createFont();
        bodyFont.setFontName("宋体");
        bodyFont.setColor(HSSFColor.BLACK.index);
        bodyFont.setFontHeightInPoints((short) 12);
        bodyStyle.setFont(bodyFont);

        String[] headsArr ={"序号","开始时间","最终执行时间(毫秒)","执行sql","原始内容"};

        XSSFRow row = sheet.createRow(0);
        //第一步：填充表头
        for (int i = 0; i < headsArr.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(headsArr[i]);
            cell.setCellStyle(titleStyle);
            if (i==0){
                sheet.setColumnWidth(i, (8* 256));
                continue;
            }
            sheet.setColumnWidth(i, (20* 256));

        }
        //冻结第一行
        sheet.createFreezePane( 0, 1, 0, 1 );
      FileReader reader = new FileReader(fileName);
      BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            List<Tomcat> sqlObjs=new ArrayList<>();
            //进入sql区域标记
            boolean isSqlStart=false;
            boolean iscatalina=false;
            String line;
            int orders=0;
            Tomcat sqlObj = new Tomcat();
            String[] orginContent=new String[3];
            while ((line = br.readLine()) != null) {

                // 一次读入一行数据
                if ("".equals(line)){
                    isSqlStart=false;
                    continue;
                }
                if(line.contains("catalina-exec")){
                    iscatalina=true;
                    isSqlStart=true;
                    String startTime = line.substring(0, 8);
                    if(startTime.contains("-"))
                        startTime=line.substring(0,19);
                    orginContent[0]=line;

                    sqlObj.setStartTime(startTime);
                }else if(isSqlStart && iscatalina){
                    String sqlStr=line;
                    orginContent[1]=line;
                    sqlObj.setSqlStr(sqlStr);
                    isSqlStart=false;
                } else if(line.contains("executed in") && iscatalina){
                    iscatalina=false;
                    int start = "\t{executed in ".length();
                    int end = line.indexOf(" msec");
                    Integer finalTime = Integer.parseInt(line.substring(start, end));
                    //过滤执行时间
                    if(finalTime<=maxSeconds)
                        continue;
                    orders++;
                    sqlObj.setOrders(orders);
                    orginContent[2]=line;
                    sqlObj.setFinalTime(finalTime);
                    if(orginContent.length==3) {
                        sqlObj.setOrginContent(orginContent[0]+orginContent[1]+orginContent[2]);
                        orginContent = new String[3];
                    }
                    System.out.println(sqlObj);
                    row = sheet.createRow(sqlObj.getOrders());
                    XSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue(sqlObj.getOrders());
                    cell1.setCellStyle(bodyStyle);
                    XSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(sqlObj.getStartTime());
                    cell2.setCellStyle(bodyStyle);
                    XSSFCell cell3 = row.createCell(2);
                    cell3.setCellValue(sqlObj.getFinalTime());
                    cell3.setCellStyle(bodyStyle);
                    XSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue(new String(sqlObj.getSqlStr().getBytes("gbk"),"utf-8"));
                    cell4.setCellStyle(bodyStyle);
                    XSSFCell cell5   = row.createCell(4);
                    cell5.setCellValue(new String(sqlObj.getOrginContent().getBytes("gbk"),"utf-8"));
                    cell5.setCellStyle(bodyStyle);
                }

            }



            File file = new File(savePath+".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        reader.close();
        br.close();
    }

    public void ngnix(String fileName,String savePath,String seconds) throws Exception{
        Double maxSeconds=Double.parseDouble(seconds);
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

        String line="";
        int sheetIndex=0;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.execute("");
        Ngnix ngnix = new Ngnix();
        waiceng:while (line!=null) {
            //创建新的Excel工作簿
            int orders=0;
            XSSFSheet sheet = workbook.createSheet("sheet"+sheetIndex);
            // 标题样式
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            // 设置水平居中
            titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 设置垂直居中
            titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 标题字体
            XSSFFont titleFont = workbook.createFont();
            titleFont.setFontName("微软雅黑");
            titleFont.setColor(HSSFColor.BLACK.index);
            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            titleFont.setFontHeightInPoints((short) 12);
            // 把字体应用到当前的样式
            titleStyle.setFont(titleFont);

            // 正文样式
            XSSFCellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.cloneStyleFrom(titleStyle);
            // 正文字体
            XSSFFont bodyFont = workbook.createFont();
            bodyFont.setFontName("宋体");
            bodyFont.setColor(HSSFColor.BLACK.index);
            bodyFont.setFontHeightInPoints((short) 12);
            bodyStyle.setFont(bodyFont);

            String[] headsArr ={"序号","客户端IP","主机本地时间","请求类型","请求路径","状态码","服务器响应字节数(byte)",
                    "请求所在页面","客户端设备","请求耗时(秒)","请求体内容","用户ID"};

            XSSFRow row = sheet.createRow(0);
            //第一步：填充表头
            for (int i = 0; i < headsArr.length; i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(headsArr[i]);
                cell.setCellStyle(titleStyle);
                if (i==0){
                    sheet.setColumnWidth(i, (8* 256));
                    continue;
                }
                sheet.setColumnWidth(i, (20* 256));

            }
            //冻结第一行
            sheet.createFreezePane( 0, 1, 0, 1 );

            while ((line = br.readLine()) != null) {
                if ("".equals(line))
                    continue;

                int first = line.indexOf("\"");
                int second = line.indexOf("\"", first + 1);
                int three = line.indexOf("\"", second + 1) + 1;
                int four = line.indexOf("\"", three + 1);
                int five = line.indexOf("\"", four + 1) + 1;
                int six = line.indexOf("\"", five + 1);
                int seven = line.indexOf("\"", six + 1);
                int eight = line.indexOf("\"", seven + 1);
                int nine = line.indexOf("\"", eight + 1) + 1;
                int ten = line.indexOf("\"", nine + 1);

                String clinetIP = line.substring(0, line.indexOf(" - -"));
                String localTime = line.substring(line.lastIndexOf("+") - 19, line.lastIndexOf("+"));
                String requestType = null;

                String request = line.substring(first, second);
                if (request.contains("GET"))
                    requestType = "GET";
                else if (request.contains("POST"))
                    requestType = "POST";
                else if (request.contains("PUT"))
                    requestType = "PUT";
                else if (request.contains("OPTIONS"))
                    requestType = "OPTIONS";
                else if (request.contains("CONNECT"))
                    requestType = "CONNECT";
                else if (request.contains("PROPFIND"))
                    requestType = "PROPFIND";
                else if (request.contains("TNBA"))
                    requestType = "TNBA";
                else if (request.contains("KYXG"))
                    requestType = "KYXG";
                else if (request.contains("HEAD"))
                    requestType = "HEAD";

                System.out.println(request);
                String requestUrl = "";
                String statusCode = "";
                String responseByte = "";
                if (request.contains("HTTP/1.1")) {
                    requestUrl = request.substring(request.indexOf(requestType + " ") + requestType.length() + 1, request.indexOf(" HTTP/1.1"));
                    statusCode = line.substring(line.indexOf("HTTP/1.1\" ") + 10, line.indexOf("HTTP/1.1\" ") + 13);
                    responseByte = line.substring(line.indexOf("HTTP/1.1\" 200 ") + 14, four - 1);
                } else if (request.contains("HTTP/1.0")) {
                    requestUrl = request.substring(request.indexOf(requestType + " ") + requestType.length() + 1, request.indexOf(" HTTP/1.0"));
                    statusCode = line.substring(line.indexOf("HTTP/1.0\" ") + 10, line.indexOf("HTTP/1.0\" ") + 13);
                    responseByte = line.substring(line.indexOf("HTTP/1.0\" 200 ") + 14, four - 1);
                }


                String requestPage = line.substring(three, four);


                String clientEquipment = line.substring(five, six);
                System.out.println(line);
                System.out.println(eight+"----"+nine);
                String timeStr = line.substring(eight + 1, nine - 27);

                System.out.println(timeStr);

                Double costTime = Double.parseDouble(timeStr);
                if (costTime <= maxSeconds)
                    continue;

                orders++;
                ngnix.setOrders(orders);
                String requestBody = line.substring(nine, ten);
                String userId = line.substring(line.lastIndexOf("\" ") + 2);

                ngnix.setClientIP(clinetIP);
                //ngnix.setLocalTime(localTime);
                ngnix.setRequestType(requestType);
                ngnix.setRequestUrl(requestUrl);
                ngnix.setStutasCode(statusCode);
                ngnix.setResponseByte(responseByte);
                ngnix.setRequestPage(requestPage);
                ngnix.setClientEquipment(clientEquipment);
                ngnix.setCostTime(costTime);
                ngnix.setRequestBody(requestBody);
               // ngnix.setUserId(userId);
                System.out.println(ngnix);


                row = sheet.createRow(ngnix.getOrders());

                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(ngnix.getOrders());
                cell0.setCellStyle(bodyStyle);

                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(ngnix.getClientIP());
                cell1.setCellStyle(bodyStyle);

                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(ngnix.getLocalTime());
                cell2.setCellStyle(bodyStyle);

                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(ngnix.getRequestType());
                cell3.setCellStyle(bodyStyle);

                XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(ngnix.getRequestUrl());
                cell4.setCellStyle(bodyStyle);

                XSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(ngnix.getStutasCode());
                cell5.setCellStyle(bodyStyle);

                XSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(ngnix.getResponseByte());
                cell6.setCellStyle(bodyStyle);

                XSSFCell cell7 = row.createCell(7);
                cell7.setCellValue(ngnix.getRequestPage());
                cell7.setCellStyle(bodyStyle);

                XSSFCell cell8 = row.createCell(8);
                cell8.setCellValue(ngnix.getClientEquipment());
                cell8.setCellStyle(bodyStyle);

                XSSFCell cell9 = row.createCell(9);
                cell9.setCellValue(ngnix.getCostTime());
                cell9.setCellStyle(bodyStyle);

                XSSFCell cell10 = row.createCell(10);
                cell10.setCellValue(ngnix.getRequestBody());
                cell10.setCellStyle(bodyStyle);

                XSSFCell cell11 = row.createCell(11);
                cell11.setCellValue(ngnix.getUserId());
                cell11.setCellStyle(bodyStyle);

                if(orders==1048575) {
                    sheetIndex++;
                    continue waiceng;
                }
            }
        }

        File file = new File(savePath+".xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        reader.close();
        br.close();
    }

    public void ngnixToMysql(String fileName,String savePath,String seconds) throws Exception{
        Double maxSeconds=Double.parseDouble(seconds);
        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        DruidDataSource druidDataSource = new DruidDataSource();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        druidDataSource.setUrl("jdbc:mysql://192.168.0.120:3306/log_analysis?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(druidDataSource);
        String line="";

        Ngnix ngnix = new Ngnix();
        while (line!=null) {
            int orders=0;

            while ((line = br.readLine()) != null) {
                if ("".equals(line))
                    continue;

                int first = line.indexOf("\"");
                int second = line.indexOf("\"", first + 1);
                int three = line.indexOf("\"", second + 1) + 1;
                int four = line.indexOf("\"", three + 1);
                int five = line.indexOf("\"", four + 1) + 1;
                int six = line.indexOf("\"", five + 1);
                int seven = line.indexOf("\"", six + 1);
                int eight = line.indexOf("\"", seven + 1);
                int nine = line.indexOf("\"", eight + 1) + 1;
                int ten = line.indexOf("\"", nine + 1);

                String clinetIP = line.substring(0, line.indexOf(" - -"));
                String localTimeStr =line.substring(nine-27,nine-8).replaceAll("T"," ");
                Date localTime = sdf.parse(localTimeStr);
                String requestType = null;

                String request = line.substring(first, second);
                if (request.contains("GET"))
                    requestType = "GET";
                else if (request.contains("POST"))
                    requestType = "POST";
                else if (request.contains("PUT"))
                    requestType = "PUT";
                else if (request.contains("OPTIONS"))
                    requestType = "OPTIONS";
                else if (request.contains("CONNECT"))
                    requestType = "CONNECT";
                else if (request.contains("PROPFIND"))
                    requestType = "PROPFIND";
                else if (request.contains("TNBA"))
                    requestType = "TNBA";
                else if (request.contains("KYXG"))
                    requestType = "KYXG";
                else if (request.contains("HEAD"))
                    requestType = "HEAD";

                if(!"POST".equals(requestType))
                    continue;

                System.out.println(request);
                String requestUrl = "";
                String statusCode = "";
                String responseByteStr = "";
                if (request.contains("HTTP/1.1")) {
                    requestUrl = request.substring(request.indexOf(requestType + " ") + requestType.length() + 1, request.indexOf(" HTTP/1.1"));
                    statusCode = line.substring(line.indexOf("HTTP/1.1\" ") + 10, line.indexOf("HTTP/1.1\" ") + 13);
                    responseByteStr = line.substring(line.indexOf("HTTP/1.1\"") + 14, three - 1-1);
                } else if (request.contains("HTTP/1.0")) {
                    requestUrl = request.substring(request.indexOf(requestType + " ") + requestType.length() + 1, request.indexOf(" HTTP/1.0"));
                    statusCode = line.substring(line.indexOf("HTTP/1.0\" ") + 10, line.indexOf("HTTP/1.0\" ") + 13);
                    responseByteStr = line.substring(line.indexOf("HTTP/1.0\"") + 14, three - 1-1);
                }


                String requestPage = line.substring(three, four);


                String clientEquipment = line.substring(five, six);
                System.out.println(line);
                System.out.println(eight+"----"+nine);
                String timeStr = line.substring(eight + 1, nine - 27);

                System.out.println(timeStr);

                Double costTime = Double.parseDouble(timeStr);
                if (costTime <= maxSeconds)
                    continue;

                orders++;
                ngnix.setOrders(orders);
                String requestBody = line.substring(nine, ten);
                if("-".equals(requestBody) || "".equals(requestBody))
                    continue;
                if(requestBody.contains("WebKitFormBoundary"))
                    continue;

                String userIdStr = line.substring(line.lastIndexOf("\" ") + 2);
                Long userId=null;

                if(userIdStr!=null && !"".equals(userIdStr) && !"-".equals(userIdStr))
                    userId=Long.parseLong(userIdStr);
                requestUrl=URLDecoder.decode(requestUrl,"utf-8");
                try{
                    requestBody=URLDecoder.decode(requestBody,"utf-8");
                }catch (Exception e){
                    ScriptEngineManager sem = new ScriptEngineManager();
                    ScriptEngine engine = sem.getEngineByExtension("js");
                     requestBody = engine.eval("unescape('"+requestBody+"')").toString();
                }

                ngnix.setClientIP(clinetIP);
                ngnix.setLocalTime(localTime);
                ngnix.setRequestType(requestType);
                ngnix.setRequestUrl(requestUrl);
                ngnix.setStutasCode(statusCode);
                ngnix.setResponseByte(responseByteStr);
                ngnix.setRequestPage(requestPage);
                ngnix.setClientEquipment(clientEquipment);
                ngnix.setCostTime(costTime);
                ngnix.setRequestBody(requestBody);
                ngnix.setUserId(userId);
                System.out.println(ngnix);
                Long responseByte=null;
                System.out.println(responseByteStr);
                if(responseByteStr!=null && !"".equals(responseByteStr) && !"-".equals(responseByteStr) )
                     responseByte= Long.parseLong(responseByteStr);

                String sql = "insert into nginx_logs (id,client_ip,vhost_local_date," +
                        "request_type,request_url,stutas_code,response_byte,request_page,client_equipment,request_wastage,request_body,user_id)" +
                        " values(?,?,?,?,?,?,?,?,?,?,?,?)";
                try{
                    jdbcTemplate.update(sql,new Object[]{null,clinetIP,localTime,requestType,requestUrl,statusCode,responseByte,requestPage,clientEquipment,costTime,requestBody,userId});
                }catch (Exception e){
                }
            }
        }

    }
}
