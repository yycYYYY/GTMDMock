package com.gtmdmock.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gtmdmock.admin.model.mapper")
public class MockAdminApplication {

	//TODO:项目还有一些系需要做的功能：1、每天凌晨删除项目内的全量log。2、项目、期望集、期望还需要添加开关按钮，这个好加，只是还需要改库以及各种相关类，比较恶心

	public static void main(String[] args) {
		SpringApplication.run(MockAdminApplication.class, args);
	}

}
