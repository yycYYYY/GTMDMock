package com.gtmdmock.admin;

import com.gtmdmock.admin.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockAdminApplicationTests {

	@Autowired
	ProjectService service;

	@Test
	void contextLoads() {
		service.getAllProjects();
	}

}
