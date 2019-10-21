package com.daishaowen.test.globalIntercepter;

import com.daishaowen.test.chinaMobile.annotationResolve.action.AuthenticationAction;
import com.daishaowen.test.chinaMobile.annotationResolve.action.AuthorityAction;
import com.daishaowen.test.chinaMobile.annotationResolve.action.IWorkAction;
import com.daishaowen.test.chinaMobile.annotationResolve.action.ValidateAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport{

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截器按照顺序执行/user/**表示只对user下的所有方法拦截
        registry.addInterceptor(new Intercepter1()).addPathPatterns("/exception/error");
        registry.addInterceptor(new Intercepter2()).addPathPatterns("/exception/error1");
        //registry.addInterceptor(securityIntercepter()).addPathPatterns("/xianLiu/**","/toupiao/**");//.excludePathPatterns("/login","/logout")排除拦截;
        List<IWorkAction> iWorkActions = new ArrayList<>();
        iWorkActions.add(new ValidateAction());
        //iWorkActions.add(new AuthorityAction());
        //iWorkActions.add(new AuthenticationAction());
//        FrameworkInterceptor frameworkInterceptor = new FrameworkInterceptor();
//        frameworkInterceptor.setWorkActions(iWorkActions);
//        registry.addInterceptor(frameworkInterceptor).addPathPatterns("/**/**");
        super.addInterceptors(registry);
    }

    //要想在拦截器中注入其它类不为null，拦截器本身要在这里初始化
//    @Bean
//    public SecurityIntercepter securityIntercepter(){
//        return new SecurityIntercepter();
//    }
}
