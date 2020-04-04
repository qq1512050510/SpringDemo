package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//未配置数据源启动 (exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
