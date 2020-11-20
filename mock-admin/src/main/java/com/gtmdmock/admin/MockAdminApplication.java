package com.gtmdmock.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gtmdmock.admin.model.mapper")
public class MockAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockAdminApplication.class, args);
	}

}
