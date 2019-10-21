package com.daishaowen.test.globalException;

import com.alibaba.fastjson.JSONObject;
import com.daishaowen.test.canjiacore.entity.JSONResult;
import com.daishaowen.test.xykd.response.RestResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)//表示运行时异常捕获
    @ResponseBody//如果返回页面的话直接返回String就可以了
    public Object resultError(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Exception e){
        if(isAjax(request)){
            StackTraceElement ste =e.getStackTrace()[0];
            System.out.println(ste.getClassName());//com.resto.brand.web.service.impl.WechatFaPiaoServiceImpl
            System.out.println(ste.getFileName());//WechatFaPiaoServiceImpl.java
            System.out.println(ste.getLineNumber());//198
            System.out.println(ste.getMethodName());//test


            //拦截参数非法异常
            if(e instanceof ConstraintViolationException){
                JSONObject jsonObject = validation(e);
                return jsonObject;
            }

            //校验入参格式
            if(e instanceof HttpMessageNotReadableException){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("errorCode", 400);
                jsonObject.put("msg", "入参格式错误");
                return jsonObject;
            }

            //其它异常
            e.printStackTrace();
            JSONObject json  = new JSONObject();
            json.put("msg",e.getMessage());
            return json;
        }else {
            //拦截参数非法异常
            if(e instanceof ConstraintViolationException){
                JSONObject validation = validation(e);
                return validation;
            }

            //校验入参格式
            if(e instanceof HttpMessageNotReadableException){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("errorCode", 400);
                jsonObject.put("msg", "入参格式错误");
            }

            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("errorCode", "500");
            result.put("errorMsg", e.getMessage());
            return result;
        }
    }

    public static boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") !=null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    //输出参数非法异常信息
    public JSONObject validation(Exception exception){
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",400 );
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<?> item : violations) {
                /**打印验证不通过的信息*/
                if(item.getMessage().contains("Required request body is missing") ||
                        item.getMessage().contains("is not present")){
                    builder.append("入参格式错误");
                }else {
                    builder.append(item.getMessage()+",");
                }

                System.out.println(item.getMessage());
            }
        String s = builder.toString();
        jsonObject.put("msg",s.substring(0,s.length()-1 ));
        return  jsonObject;
    }
}

