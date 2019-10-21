package com.daishaowen.test.controller;


import com.daishaowen.test.canjiacore.entity.JSONResult;
import com.daishaowen.test.canjiacore.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by disvenk.dai on 2018-06-07 17:35
 */
@RestController
@Slf4j
@RequestMapping("cookie")
public class CookieController {

        private static final Logger logger = LoggerFactory.getLogger(CookieController.class);

    @RequestMapping("setCookie")
    //@ExApiIdempotent(type="header")
    public String setCookie(HttpServletRequest request, HttpServletResponse response,String token){

        logger.info("设置cookie");
        Lock lock= new ReentrantLock();
        List list = new ArrayList<>();
        request.getSession().setAttribute("cookie","代绍文");
        //throw new BusinessException(ExceptionEnum.BASE_CHECK_EXCP.setExceptionMsg("chu0"));
        return "setCookieSuccess";
    }


    @RequestMapping("setCookie1")
    public Object setCookie1(HttpServletRequest request, HttpServletResponse response,String callback){

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new JSONResult("disvenk"));
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

    @RequestMapping("getCookie")
    public String getCookie(HttpServletRequest request){
        String cookie = request.getSession().getAttribute("cookie").toString();
        System.out.println(cookie);
        return "getCookieSuccess";
    }

    @RequestMapping(value="/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback){
        //TaotaoResult result = userLoginServiceImpl.getUserByToken(jwt);
        if(StringUtils.isNotBlank(callback)){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(null);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;

        }

        //return result;
        return null;
    }
}
