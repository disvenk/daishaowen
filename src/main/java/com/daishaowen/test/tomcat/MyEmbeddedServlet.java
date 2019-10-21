//package com.daishaowen.test.tomcat;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.catalina.valves.AccessLogValve;
//import org.apache.coyote.http11.Http11NioProtocol;
//import org.springframework.boot.context.embedded.Compression;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.Ssl;
//import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
///**
// * Created by disvenk.dai on 2018-08-24 11:10
// */
//@Component
//public class MyEmbeddedServlet implements EmbeddedServletContainerCustomizer {
//
//    @Override
//    public void customize(ConfigurableEmbeddedServletContainer container) {
//        TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory) container;
//        /*server配置*/
//        factory.setPort(8082);//设置端口
//        factory.setContextPath("/test");//设置根路径
//        //factory.setAddress();//指定server绑定的地址
//
//        //Compression compression = new Compression();
//        //compression.setEnabled(false);//是否开启压缩，默认为false.
//       // compression.setExcludedUserAgents(new String[]{});//指定不压缩的user-agent，多个以逗号分隔，默认值为:text/html,text/xml,text/plain,text/css
//        //compression.setMimeTypes(new String[]{});//指定要压缩的MIME type，多个以逗号分隔.
//        //compression.setMinResponseSize(2048);//执行压缩的阈值，默认为2048
//        //factory.setCompression(compression);;
//       // factory.setDisplayName("");//设定应用的展示名称，默认: application
//        //Ssl ssl = new Ssl();
//       // ssl.setCiphers(new String[]{});
//       // ssl.setEnabled(true);
//       // factory.setSsl(ssl);
//
//
//        factory.setBaseDirectory(new File("F:/temps"));//设置工作记录目录
//        factory.addEngineValves(getLogAccessLogValue());//设置日志(并不是事实记录)
//        factory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());//设置连接数
//        factory.addErrorPages(new org.springframework.boot.web.servlet.ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));//添加错误页面
//        //初始化一些设置
//        factory.addInitializers((servletContext ->{
//            System.out.println("server start!");
//            System.out.println(" = = = = 获取服务器信息 = = " + servletContext.getServerInfo());
//        }));
//    }
//
//
//    private AccessLogValve getLogAccessLogValue() {
//        AccessLogValve log = new AccessLogValve();
//        //日志目录
//        log.setDirectory("F:/temps");
//        //开始日志
//        log.setEnabled(true);
//        //日志级别
//        log.setPattern("common");
//        //日志前缀
//        log.setPrefix("springboot-disvenk-log");
//        //日志后缀
//        log.setSuffix(".log");
//        return log;
//    }
//}
//class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
//
//    @Override
//    public void customize(Connector connector) {
//        //连接协议 HTTP/1.1
//        System.out.println(connector.getProtocol());
//        //连接协议处理器 org.apache.coyote.http11.Http11NioProtocol
//        System.out.println(connector.getProtocolHandler().getClass());
//        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//        //设置最大连接数
//        protocol.setMaxConnections(2000);
//        //设置最大线程数
//        protocol.setMaxThreads(500);
//        protocol.setMinSpareThreads(500);
//        protocol.setConnectionTimeout(30000);
//        //protocol.setSSLCACertificateFile("");
//        //protocol.setSSLCACertificatePath("");
//        //所有的参数都可以在这里设置
//    }
//
//}
