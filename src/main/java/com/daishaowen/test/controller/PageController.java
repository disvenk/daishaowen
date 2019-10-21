package com.daishaowen.test.controller;



import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class PageController {

    @RequestMapping("test/index")
    @ResponseBody
    public JSONObject indexHtml(){
        System.out.println("888888");
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");
        try{
            int a =1/0;
        }catch (Exception e){
            log.error("异常：{}",e);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","你能跨域吗");

        jsonObject.toJSONString();
        return jsonObject;
    }

    @RequestMapping("/index1.html")
    @ResponseBody
    public String index1(){
        return "还能跨域吗";
    }

    public static void main(String[] args) {

    }
}
