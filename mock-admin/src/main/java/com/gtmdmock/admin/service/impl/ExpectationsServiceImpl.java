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
    private ExpectationsMapper expectationsMapper;

    @Override
    public void insertExpectations(Expectations expectations) {
        expectationsMapper.insert(expectations);
    }

    @Override
    public void updateExpectations(Expectations expectations) {
        expectationsMapper.updateByPrimaryKey(expectations);
    }

    @Override
    public void deleteExpectationsById(Integer id) {
        expectationsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertExpectationsToCore(Expectations expectations) {
        this.insertExpectations(expectations);
        //TODO:  insertExpectationsToCore
    }

    @Override
    public void updateExpectationsOfCore(Expectations expectations) {
        this.updateExpectations(expectations);
//        TODO:updateExpectationsToCore
    }

    @Override
    public void deleteExpectationsOfCoreById(Integer id) {
        this.deleteExpectationsById(id);
//        TODO:deleteExpectationsOfCoreById
    }

    @Override
    public Expectations getProjectById(Integer id) {
        return expectationsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Expectations> getAllExpectationsOfAdmin() {
        ExpectationsExample example = new ExpectationsExample();
        example.createCriteria().andIdIsNotNull();
        return expectationsMapper.selectByExample(example);
    }

    @Override
    public List<ExpectationsTemplate> getAllExpectationsOfCore() {
        List<ExpectationsTemplate> templates = new ArrayList<>();
        List<Expectations> expectationsList = getAllExpectationsOfAdmin();
        for (Expectations expectations: expectationsList){
            ExpectationsTemplate template = new ExpectationsTemplate();
            Optional.ofNullable(expectations.getProjectId()).ifPresent(template::setProjectId);
            Optional.ofNullable(expectations.getId()).ifPresent(template::setExpectationsId);

            templates.add(template);
        }
        return templates;
    }
}
