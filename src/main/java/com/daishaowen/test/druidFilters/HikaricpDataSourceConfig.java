//package com.daishaowen.test.druidFilters;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatisPlugin.spring.SqlSessionFactoryBean;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.UrlResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.interceptor.*;
//
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableConfigurationProperties(HikaricpProperties.class)
//public class HikaricpDataSourceConfig {
//	private static final int TX_METHOD_TIMEOUT = 5;
//	private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.daishaowen.test..service.*.*(..))";
//
//	@Autowired
//	private HikaricpProperties shardDataSourceProperties;
//
////	@Bean
////	public DataSource dataSource(){
////		DruidDataSource druidDataSource = new DruidDataSource();
////		try {
////			druidDataSource.setFilters("spring.datasource.filters下的值");
////		} catch (SQLException e) {
////			System.err.println("druid configuration initialization filter: "+ e);
////		}
////		return druidDataSource;
////	}
//
//	@Bean
//	public HikariDataSource parentDs() throws SQLException {
//		HikariDataSource ds = new HikariDataSource();
//		ds.setDriverClassName(shardDataSourceProperties.getDriverClassName());
//		//ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//		ds.setJdbcUrl(shardDataSourceProperties.getUrl());
//		ds.setUsername(shardDataSourceProperties.getUsername());
//		ds.setPassword(shardDataSourceProperties.getPassword());
//		//ds.setDataSource();//这个属性允许你直接设置数据源的实例被池包装，而不是让HikariCP通过反射来构造它
//		//ds.setHealthCheckRegistry();//该属性允许您指定池使用的Codahale / Dropwizard HealthCheckRegistry的实例来报告当前健康信息
//		//ds.setConnectionTimeout(30000);//等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
//		//ds.setIdleTimeout(10);//一个连接空闲状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟
//		//ds.setLoginTimeout(30);
//		//ds.setLogWriter();
//		//ds.setMetricRegistry();//该属性允许您指定一个 Codahale / Dropwizard MetricRegistry 的实例，供池使用以记录各种指标
//		//ds.setMetricsTrackerFactory();
//		//ds.setDataSourceClassName();
//		//ds.setMaximumPoolSize(100);//连接池中允许的最大连接数。缺省值：10；推荐的公式：((core_count * 2) + effective_spindle_count)
//		//ds.setMinimumIdle(100);//最小空闲连接数,HikariCP 建议我们不要设置此值,而是充当固定大小的连接池与maximumPoolSize数值相同
//		//ds.setMaxLifetime();//一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，建议设置比数据库超时时长少30秒，参考MySQL wait_timeout参数（show variables like '%timeout%';）
//		//ds.setPoolName();//连接池名称,主要用于显示在日志记录和 JMX 管理控制台中auto-generated
//		//ds.setValidationTimeout(5000);//连接将被测试活动的最大时间量
//		//ds.setTransactionIsolation();//控制连接的事务隔离等级 default:none
//		//ds.setReadOnly(false);//连接只读数据库时配置为true， 保证安全
//		//ds.setAllowPoolSuspension(true);//设置缓冲池允许暂停功能是否启用
//		//ds.setAutoCommit(true);//控制自动提交行为 default：true
//		//ds.setCatalog();//设置 catalog 以便于支持查看 catalogs
//		//ds.setConnectionInitSql("select 1");//在连接被创建的时候并且在连接加入到缓冲池之前,设置将要被执行的SQL字符串
//		//ds.setConnectionTestQuery();//如果您的驱动程序支持JDBC4，我们强烈建议您不要设置此属性
//		//ds.setLeakDetectionThreshold(0);//用来设置连接被占用的超时时间，单位为毫秒，默认为0，表示禁用连接泄露检测
//		return ds;
//	}
//
////	 mybatis文件配置，扫描所有mapper文件
////	<bean id="sqlSessionFactory" class="org.mybatisPlugin.spring.SqlSessionFactoryBean"
////	p:dataSource-ref="dataSource" p:configLocation="classpath:mybatisPlugin-config.xml"
////	p:mapperLocations="classpath:com/resto/brand/web/dao*.xml" />
//	@Bean
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(parentDs());
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/daishaowen/test/mapper/*.xml"));
//		URL url = HikaricpDataSourceConfig.class.getClassLoader().getResource("mybatisPlugin-config.xml");
//        sqlSessionFactoryBean.setConfigLocation(new UrlResource(url));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//
//	// 对dataSource 数据源进行事务管理
//	//<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager" p:dataSource-ref="dataSource" />
//    @Bean
//    public PlatformTransactionManager transactionManager() throws SQLException {
//        return new DataSourceTransactionManager(parentDs());
//    }
//
//
//     //事务管理 通知
////	<tx:advice id="txAdvice" transaction-manager="transactionManager">
////		<tx:attributes>
////			<!-- 对insert,update,delete 开头的方法进行事务管理,只要有异常就回滚 -->
////			<tx:method name="insert*" propagation="REQUIRED"
////	rollback-for="java.lang.Throwable" />
////			<tx:method name="update*" propagation="REQUIRED"
////	rollback-for="java.lang.Throwable" />
////			<tx:method name="delete*" propagation="REQUIRED"
////	rollback-for="java.lang.Throwable" />
////			<!-- select,count开头的方法,开启只读,提高数据库访问性能 -->
////			<tx:method name="select*" read-only="true" />
////			<tx:method name="count*" read-only="true" />
////			<!-- 对其他方法 使用默认的事务管理 -->
////			<tx:method name="*" />
////		</tx:attributes>
////	</tx:advice>
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
//
//	//织入
//	//	<!-- 註解式事務的支持 -->  注解事务和xml的优先级,aop和事务的优先级可以通过order属性设置
////			    <tx:annotation-driven transaction-manager="txManager"  order="0"/>  
////			    <!-- 服務事務註冊切面 -->  
////			    <aop:config >  
////			        <aop:pointcut expression="execution(* oddtech.service.impl.*.*(..))"  
////			            id="txPoint"  />  
////			        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"  order="1"/>  
////			    </aop:config>
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
//
//
//
//
//}
