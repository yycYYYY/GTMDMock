package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.expectation.ExpectationsTemplate;

import java.util.List;

public interface ExpectationsService {


    Expectations getProjectById();

    void updateExpectations(Expectations expectations);

    void deleteExpectationsById(Integer id);

    List<Expectations> getAllExpectationsOfAdmin();

    List<ExpectationsTemplate> getAllExpectationsOfCore();
}
