package com.daishaowen.test.filedownload;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileDownLoad {
    @RequestMapping("downAppendix")
    @ResponseBody
    public void downLoad(HttpServletResponse response,
                         HttpServletRequest request, Long appendixId) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("appendixId",appendixId);
        //http://192.168.0.121/group1/M00/00/00/wKgAeV1dFmyAYtnKABBOkoFd1no57.xlsx
        HttpClient client = new DefaultHttpClient();
        String fileUrl = "http://192.168.0.121:81/group1/M00/00/00/wKgAeV1dFmyAYtnKABBOkoFd1no57.xlsx";
        HttpGet get = new HttpGet(fileUrl);
        HttpResponse execute = null;

        execute = client.execute(get);
        //InputStream inputStream =  execute.getEntity().getContent();
        InputStream in =  execute.getEntity().getContent();
//            String path = this.getClass().getResource("/").getPath();
//
//
//
//            //获取item中的上传文件的输入流

        String fileName = "姓名测试";
//            //创建一个文件输出流
//            //FileOutputStream out = new FileOutputStream(path + fileName + appendixVo.getFileType());
//            FileOutputStream out = new FileOutputStream(path + fileName + ".xlsx");
//            //创建一个缓冲区
//            byte buffer[] = new byte[1024];
//            //判断输入流中的数据是否已经读完的标识
//            int len = 0;
//            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
//            while ((len = inputStream.read(buffer)) > 0) {
//                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
//                out.write(buffer, 0, len);
//            }
//            //关闭输入流
//            inputStream.close();
//            //关闭输出流
//            out.close();

        //String filePath = this.getClass().getResource("/" + fileName + appendixVo.getFileType()).getPath();
        // String filePath = this.getClass().getResource("/" + fileName + ".xlsx").getPath();

        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "No-Cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("*/*; charset=UTF-8");
        response.setHeader("Content-disposition","attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));// 设定输出文件头
        ServletOutputStream servletOutputStream = null;
        //FileInputStream in = new FileInputStream(filePath); // 读入文件
        servletOutputStream = response.getOutputStream();
        servletOutputStream.flush();
        int aRead = 0;
        while ((aRead = in.read()) != -1 & in != null) {
            servletOutputStream.write(aRead);
        }
        servletOutputStream.flush();
        in.close();
        servletOutputStream.close();

//            File file = new File(filePath);
//            if(file.exists()){
//                file.delete();
//            }


    }
}
