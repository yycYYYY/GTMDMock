package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Expectations;

import com.gtmdmock.admin.model.entity.ExpectationsExample;
import com.gtmdmock.admin.model.mapper.ExpectationsMapper;
import com.gtmdmock.admin.service.ExpectationsService;;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpectationsServiceImpl implements ExpectationsService {

    @Autowired
    ExpectationsMapper mapper;

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
    public List<Expectations> getAllExpectationsOfAdmin() {
        ExpectationsExample example = new ExpectationsExample();
        example.createCriteria().andIdIsNotNull();
        return mapper.selectByExample(example);
    }

    @Override
    public List<ExpectationsTemplate> getAllExpectationsOfCore() {
        List<ExpectationsTemplate> templates = new ArrayList<>();
        List<Expectations> expectationsList = getAllExpectationsOfAdmin();
        for (Expectations expectations: expectationsList){
            ExpectationsTemplate template = new ExpectationsTemplate();
            Optional.ofNullable(expectations.getProjectId()).ifPresent(template::setProjectId);

            templates.add(template);
        }
        return templates;
    }
}
