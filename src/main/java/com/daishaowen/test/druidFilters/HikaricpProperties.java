//package com.daishaowen.test.druidFilters;
//
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Data
////@PropertySource("classpath:application.yml")
//@ConfigurationProperties(prefix="hikaricp.jdbc")
//public class HikaricpProperties {
//
//	private String driverClassName;
//
//	private String url;
//
//	private String username;
//
//	private String password;
//
//	private String filters;
//
//	private int maxActive;
//
//	private int initialSize;
//
//	private int maxWait;
//
//	private int minIdle;
//
//	private int timeBetweenEvictionRunsMillis;
//
//	private int minEvictableIdleTimeMillis;
//
//	private String validationQuery;
//
//	private boolean testWhileIdle;
//
//	private boolean testOnBorrow;
//
//	private boolean testOnReturn;
//
//	private boolean poolPreparedStatements;
//
//	private int maxPoolPreparedStatementPerConnectionSize;
//
//	private boolean removeAbandoned;
//
//	private int removeAbandonedTimeout;
//
//	private boolean logAbandoned;
//
//	private List<String> connectionInitSqls;
//
//}
