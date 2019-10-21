/*
package com.daishaowen.test.crossDomain;*/
package com.daishaowen.test.crossDomain;

import com.daishaowen.test.util.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

public class DomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest res, ServletResponse req, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) req;
        HttpServletRequest request = (HttpServletRequest)res;
        //如果nginx配置了这里就不用配置了
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");//指定客户端允许发送cookie
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");//将本过滤器的设置缓存一个小时，不用在发送预检命令
//        //addHeader，如果同名header已存在，则追加至原同名header后面。setHeader，如果同名header已存在，则覆盖一个同名header
//        String headers = request.getHeader("Access-Control-Allow-Headers");
//        response.setHeader("Access-Control-Allow-Headers", headers);
//        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        //Access-Control-Expose-Headers
        //默认情况下我们在浏览器下面看到的首部信息是默认的，有些我们访问不到
        //我们可以使用上面的那个参数，然后在后面添加我们想让浏览看到的额外首部

        //cookie名字
        String cookieName = "JSESSIONID";
        //cookie编码
        String encodeString = "UTF-8";
        //该方法可以添加设置和获取cookie
        String cookieValue = CookieUtils.getCookieValue(request,cookieName,encodeString);
        //设置通用域名
        Cookie[] cookies = request.getCookies();
//        if(cookies!=null) {
//            for (Cookie cookie : cookies) {
//                cookie.setDomain(".disvenk.com");
//                cookie.setPath("/");
//            }
//        }
        chain.doFilter(res, req);
    }

    @Override
    public void destroy() {

    }
}


