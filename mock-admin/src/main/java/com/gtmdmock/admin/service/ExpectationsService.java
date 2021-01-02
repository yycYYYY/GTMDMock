package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.expectation.ExpectationsTemplate;

import java.util.List;

public interface ExpectationsService {

    void insertExpectations(Expectations expectations);

    void updateExpectations(Expectations expectations);

    void deleteExpectationsById(Integer id);

    void deleteExpectationsByProjectId(Integer projectId);

    void deleteExpectationsOfCoreById(Integer id);

    Expectations getExpectationsById(Integer id);

    List<Expectations> getAllExpectationsOfAdmin();

    List<Expectations> getAllExpectationsOfAdminByProjectId(Integer projectId);

    List<Expectations> getAllExpectationsOfAdminByProjectId(Integer projectId, Integer pageNumber, Integer pageSize);

    List<ExpectationsTemplate> getAllOpenExpectationsOfCore();
}
