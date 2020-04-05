package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;

//未配置数据源启动 (exclude = { DataSourceAutoConfiguration.class })
/*@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })*/
@SpringBootApplication
public class SpringDemoApplication {

/*	@Bean
	@ConfigurationProperties(prefix = "db")
	public DataSource dateSource() {
		DruidDataSource dataSource = new DruidDataSource();
		return (DataSource) dataSource;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
