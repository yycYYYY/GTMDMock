package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.Project;
import com.gtmdmock.admin.service.ExpectationsService;
import com.gtmdmock.core.client.ClientInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpectationsServiceImpl implements ExpectationsService {
    @Override
    public Expectations getProjectById() {
        return null;
    }

    @Override
    public void updateExpectations(Expectations expectations) {

    }

    @Override
    public void deleteExpectationsById(Integer id) {

    }

    @Override
    public List<Project> getAllExpectationsOfAdmin() {
        return null;
    }

    @Override
    public List<ClientInfo> getAllExpectationsOfCore() {
        return null;
    }
}
