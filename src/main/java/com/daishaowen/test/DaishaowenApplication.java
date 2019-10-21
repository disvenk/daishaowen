package com.daishaowen.test;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.daishaowen.test.chinaMobile.annotationResolve.resolver.JsonDtoArgumentResolver;
import com.daishaowen.test.chinaMobile.annotationResolve.resolver.ResponseForJsonProcessor;
import com.daishaowen.test.crossDomain.DomainFilter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDubbo
//@EnableDubboConfiguration
//@ServletComponentScan(basePackages = {"com.daishaowen.test.druidFilters"})//扫描filter和servlet
@EnableRedisHttpSession //开启redissession共享
@EnableScheduling //开启使用定时任务
//@ServletComponentScan //用来扫描druid的监控入口
@EnableAsync //开启异步方法
//@EnableCaching//开启二级缓存
@MapperScan(basePackages = "com.daishaowen.test.mapper")//扫描mybatis的mapper
//expose-proxy目标对象内部的自我调用实施切面增强
//配置使Spring采用CGLIB或jdk代理
//<aop:aspectj-autoproxy proxy-target-class="true" />
@EnableAspectJAutoProxy(exposeProxy =true,proxyTargetClass = true)//proxyTargetClass表示为true表示使用cglib代理
@EnableTransactionManagement //相当于之前在xml中配置的<tx:annotation-driven />注解驱动。<!-- 注解方式配置事物 -->
//<tx:annotation-driven transaction-manager="transactionManager" order="2"/>这样就实现了我们自己写的aop在事务介入之前就执行了！
//使用改注解后，只需要使用@Transactional即可，不需要一系列的繁琐配置，DataSource就使用springboot默认的配置方式即可
//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class DaishaowenApplication implements ServletContextInitializer {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DaishaowenApplication.class, args);
	}

	//增加过滤器责任链
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		DomainFilter domainFilter = new DomainFilter();
		registrationBean.setName("sessionFilter");
		registrationBean.setFilter(domainFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.addUrlPatterns();
		registrationBean.addInitParameter("cacheName", "SimplePageCachingFilter");
		registrationBean.setOrder(1);//第一个执行
		return registrationBean;
	}
	/*
		*   <filter>
        <filter-name>sessionFilter</filter-name>
        <filter-class>net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter</filter-class>
        <init-param>
            <param-name>cacheName</param-name>
            <param-value>SimplePageCachingFilter</param-value>
       </init-param>
    </filter>
    <filter-mapping>
        <filter-name>PageCachingFilter</filter-name>
        <url-pattern>/pageCacheController/testPageCache.do</url-pattern>
    </filter-mapping>
		* */

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.setInitParameter("contextConfigLocation", "disvenk");
	}
	/**
	 * <context-param>
	 * 		<param-name>contextConfigLocation</param-name>
	 * 		<param-value>disvenk</param-value>
	 * 	</context-param>
	 */

}
