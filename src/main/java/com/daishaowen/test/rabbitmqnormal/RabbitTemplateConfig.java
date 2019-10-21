//package com.daishaowen.test.rabbitmqnormal;
//
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.retry.backoff.ExponentialBackOffPolicy;
//import org.springframework.retry.support.RetryTemplate;
//
///**
// * Created by disvenk.dai on 2018-08-23 15:09
// */
//
//
//@Configuration
//public class RabbitTemplateConfig {
//
//    @Autowired
//    @Qualifier("connectionFactory")
//    ConnectionFactory connectionFactory;
//
//    @Bean
//    /** 因为要设置回调类，所以应是prototype类型，在使用延时队列是必须使用prototype类型；如果是singleton类型，则回调类为最后一次设置 */
//    /*rabbitTemplate要设置回调类，如果是singleton，回调类就只能有一个，所以如果想要设置不同的回调类，就要设置为prototype的scope。*/
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RabbitTemplate rabbitTemplatenew() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        //ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
//        //backOffPolicy.setInitialInterval(500);//重试间隔时间（单位毫秒）
//       // backOffPolicy.setMultiplier(2.0);//第一次等1秒，第二次等2秒，第三次4秒
//       // backOffPolicy.setMaxInterval(10000);
//       // RetryTemplate retryTemplate = new RetryTemplate();
//       // retryTemplate.setBackOffPolicy(backOffPolicy);
//       // template.setRetryTemplate(retryTemplate);
//        return template;
//    }
//
//}
