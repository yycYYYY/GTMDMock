package com.gtmdmock.admin;

import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.service.ProjectService;
import com.gtmdmock.admin.service.ResponseService;
import com.gtmdmock.core.response.ResponseTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockAdminApplicationTests {

	@Autowired
	ProjectService projectService;

	@Autowired
	ResponseService responseService;

	@Test
	void contextLoads() {
		projectService.getAllProjects();

	}

	@Test
	void test(){
		Response response = responseService.getResponsesByRequestId(1);
		ResponseTemplate responseTemplate = responseService.getResponseOfCore(response);
		System.out.println(responseTemplate.getRequestId());
	}


}
