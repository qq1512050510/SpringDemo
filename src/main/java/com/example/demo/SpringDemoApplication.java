package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


//未配置数据源启动 (exclude = { DataSourceAutoConfiguration.class })
/*@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })*/
@SpringBootApplication
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
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
