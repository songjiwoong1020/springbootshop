package com.jiwoong.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfiguration {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory (DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		
		
	}

}
/*
<beans:bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" >
	<beans:property name="dataSource" ref="dataSource"/>
	<beans:property name="mapperLocations" value="classpath:mybatis/mapper/*.xml" />
</beans:bean>
<beans:bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
	<beans:constructor-arg index="0" ref="sqlSessionFactory" />
</beans:bean>
*/