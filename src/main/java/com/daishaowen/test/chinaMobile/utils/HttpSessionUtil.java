package com.daishaowen.test.chinaMobile.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author jiying
 * @date 2015年9月18日
 * @version 1.0
 */
public class HttpSessionUtil {

    public static HttpSession getHttpSession() {

        HttpSession httpSession = null;

        final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        httpSession = requestAttributes.getRequest().getSession();

        return httpSession;

    }

}
