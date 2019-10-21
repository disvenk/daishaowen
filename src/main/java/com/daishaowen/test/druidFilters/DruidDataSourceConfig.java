package com.daishaowen.test.druidFilters;

import com.alibaba.druid.pool.DruidDataSource;
import com.daishaowen.test.DuXieFenLi.mybatisLanJie.DataSourceSelector;
import com.daishaowen.test.DuXieFenLi.mybatisLanJie.DynamicDataSourceTransactionManager;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidDataSourceConfig {
	private static final int TX_METHOD_TIMEOUT = 5;
	private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.daishaowen.test..service.*.*(..))";

	@Autowired
	private DruidProperties datasource;

	@Bean("dataSource1")
	public DataSource dataSource1(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl("jdbc:mysql://192.168.0.120:3306/disvenk1?useUnicode=true&characterEncoding=utf8");
		druidDataSource.setUsername(datasource.getUsername());
		druidDataSource.setPassword(datasource.getPassword());
		druidDataSource.setDriverClassName(datasource.getDriverClassName());
		druidDataSource.setInitialSize(datasource.getInitialSize());
		druidDataSource.setMaxActive(datasource.getMaxActive());
		druidDataSource.setMinIdle(datasource.getMinIdle());
		druidDataSource.setMaxWait(datasource.getMaxWait());
		druidDataSource.setTimeBetweenEvictionRunsMillis(datasource.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(datasource.getMinEvictableIdleTimeMillis());
		druidDataSource.setValidationQuery(datasource.getValidationQuery());
		druidDataSource.setTestWhileIdle(datasource.isTestWhileIdle());
		druidDataSource.setTestOnBorrow(datasource.isTestOnBorrow());
		druidDataSource.setTestOnReturn(datasource.isTestOnReturn());
		druidDataSource.setPoolPreparedStatements(datasource.isPoolPreparedStatements());
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(datasource.getMaxPoolPreparedStatementPerConnectionSize());
        Properties properties = new Properties();
        properties.put("druid.stat.slowSqlMillis", "3000");
        properties.put("druid.stat.logSlowSql", "true");
        properties.put("druid.stat.mergeSql", "true");
		druidDataSource.setConnectProperties(properties);
		druidDataSource.setUseGlobalDataSourceStat(datasource.isUseGlobalDataSourceStat());
		try {
			druidDataSource.setFilters(datasource.getFilters());
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter: "+ e);
		}
		return druidDataSource;
	}

	@Bean("dataSource2")
	public DataSource dataSource2(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl("jdbc:mysql://192.168.0.120:3306/disvenk2?useUnicode=true&characterEncoding=utf8");
		druidDataSource.setUsername(datasource.getUsername());
		druidDataSource.setPassword(datasource.getPassword());
		druidDataSource.setDriverClassName(datasource.getDriverClassName());
		druidDataSource.setInitialSize(datasource.getInitialSize());
		druidDataSource.setMaxActive(datasource.getMaxActive());
		druidDataSource.setMinIdle(datasource.getMinIdle());
		druidDataSource.setMaxWait(datasource.getMaxWait());
		druidDataSource.setTimeBetweenEvictionRunsMillis(datasource.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(datasource.getMinEvictableIdleTimeMillis());
		druidDataSource.setValidationQuery(datasource.getValidationQuery());
		druidDataSource.setTestWhileIdle(datasource.isTestWhileIdle());
		druidDataSource.setTestOnBorrow(datasource.isTestOnBorrow());
		druidDataSource.setTestOnReturn(datasource.isTestOnReturn());
		druidDataSource.setPoolPreparedStatements(datasource.isPoolPreparedStatements());
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(datasource.getMaxPoolPreparedStatementPerConnectionSize());
		Properties properties = new Properties();
		properties.put("druid.stat.slowSqlMillis", "3000");
		properties.put("druid.stat.logSlowSql", "true");
		properties.put("druid.stat.mergeSql", "true");
		druidDataSource.setConnectProperties(properties);
		druidDataSource.setUseGlobalDataSourceStat(datasource.isUseGlobalDataSourceStat());
		try {
			druidDataSource.setFilters(datasource.getFilters());
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter: "+ e);
		}
		return druidDataSource;
	}

	@Bean
	public DataSourceSelector dataSourceSelector(){
		return   new DataSourceSelector(dataSource1(),dataSource2());
	}

	@Bean
    public LazyConnectionDataSourceProxy dataSource(){
        LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy = new LazyConnectionDataSourceProxy();
        lazyConnectionDataSourceProxy.setTargetDataSource(dataSourceSelector());
        return lazyConnectionDataSourceProxy;
    }

	/**
	 * [JDBC日志拦截器]
	 *
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
//	@Bean
//	public Log4jdbcInterceptor log4jdbcInterceptor() {
//		return new Log4jdbcInterceptor();
//	}

	/**
	 * [数据资源日志代理类]
	 *
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Bean
	public BeanNameAutoProxyCreator dataSourceLog4jdbcAutoProxyCreator() {
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		creator.setInterceptorNames("log4jdbcInterceptor");
		creator.setBeanNames("dataSource1","dataSource2");
		return creator;
	}

//	 mybatis文件配置，扫描所有mapper文件
//	<bean id="sqlSessionFactory" class="org.mybatisPlugin.spring.SqlSessionFactoryBean"
//	p:dataSource-ref="dataSource" p:configLocation="classpath:mybatisPlugin-config.xml"
//	p:mapperLocations="classpath:com/resto/brand/web/dao*.xml" />
	@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/daishaowen/test/mapper/*.xml"));
		URL url = DruidDataSourceConfig.class.getClassLoader().getResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(new UrlResource(url));
        return sqlSessionFactoryBean.getObject();
    }


	// 对dataSource 数据源进行事务管理
	//<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager" p:dataSource-ref="dataSource" />
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }


     //事务管理 通知
//	<tx:advice id="txAdvice" transaction-manager="transactionManager">
//		<tx:attributes>
//			<!-- 对insert,update,delete 开头的方法进行事务管理,只要有异常就回滚 -->
//			<tx:method name="insert*" propagation="REQUIRED"
//	rollback-for="java.lang.Throwable" />
//			<tx:method name="update*" propagation="REQUIRED"
//	rollback-for="java.lang.Throwable" />
//			<tx:method name="delete*" propagation="REQUIRED"
//	rollback-for="java.lang.Throwable" />
//			<!-- select,count开头的方法,开启只读,提高数据库访问性能 -->
//			<tx:method name="select*" read-only="true" />
//			<tx:method name="count*" read-only="true" />
//			<!-- 对其他方法 使用默认的事务管理 -->
//			<tx:method name="*" />
//		</tx:attributes>
//	</tx:advice>
//	@Bean
//	public TransactionInterceptor txAdvice() throws SQLException {
//		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//		/*只读事务，不做更新操作*/
//		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//		readOnlyTx.setReadOnly(true);
//		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
//
//		/*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
//		RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
//		requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//		requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		requiredTx.setTimeout(TX_METHOD_TIMEOUT);
//
//		Map<String, TransactionAttribute> txMap = new HashMap<>();
//		txMap.put("add*", requiredTx);
//		txMap.put("save*", requiredTx);
//		txMap.put("insert*", requiredTx);
//		txMap.put("update*", requiredTx);
//		txMap.put("delete*", requiredTx);
//
//		//对查询数据开启只读
//		txMap.put("get*", readOnlyTx);
//		txMap.put("query*", readOnlyTx);
//
//		source.setNameMap( txMap );
//		TransactionInterceptor txAdvice = new TransactionInterceptor();
//		txAdvice.setTransactionManager(transactionManager());
//		txAdvice.setTransactionAttributeSource(source);
//		return txAdvice;
//
//	}

	//织入
	//	<!-- 註解式事務的支持 -->  注解事务和xml的优先级,aop和事务的优先级可以通过order属性设置
//			    <tx:annotation-driven transaction-manager="txManager"  order="0"/>  
//			    <!-- 服務事務註冊切面 -->  
//			    <aop:config >  
//			        <aop:pointcut expression="execution(* oddtech.service.impl.*.*(..))"  
//			            id="txPoint"  />  
//			        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"  order="1"/>  
//			    </aop:config>
//	@Bean
//	public Advisor txAdviceAdvisor() throws SQLException {
//		//配置切点
//		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//
//		//将通知织入切点
//		DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
//		defaultPointcutAdvisor.setAdvice(txAdvice());
//		defaultPointcutAdvisor.setPointcut(pointcut);
//		//defaultPointcutAdvisor.setOrder(1);
//		return defaultPointcutAdvisor;
//	}




}
