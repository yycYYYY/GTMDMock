package com.gtmdmock.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.ExpectationsExample;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.mapper.ExpectationsMapper;
import com.gtmdmock.admin.service.ExpectationsService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.expectation.ExpectationGenerator;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpectationsServiceImpl implements ExpectationsService {

    @Autowired
    ExpectationsMapper expectationsMapper;

    @Autowired
    RequestService requestService;

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private final ExpectationsAction expectationsAction = bootstrap.getExpectationsAction();

    private final ExpectationGenerator expectationUtils = new ExpectationGenerator();

    @Override
    public void insertExpectations(Expectations expectations) {
        expectationsMapper.insert(expectations);
        expectationsAction.addExpectationsTemplate(getExpectationsOfCore(expectations));
    }

    @Override
    public void updateExpectations(Expectations expectations) {
        expectationsMapper.updateByPrimaryKey(expectations);
    }

    @Override
    public void deleteExpectationsById(Integer id) {
        expectationsMapper.deleteByPrimaryKey(id);
        requestService.deleteRequestsByExpectationsId(id);
    }

    @Override
    public void deleteExpectationsByProjectId(Integer projectId) {
        List<Expectations> expectationsList = getAllExpectationsOfAdminByProjectId(projectId);
        for (Expectations expectations: expectationsList){
            deleteExpectationsById(expectations.getId());
        }
    }

    @Override
    public void deleteExpectationsOfCoreById(Integer id) {
        this.deleteExpectationsById(id);
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(id);
        List<Request> requestList = requestService.getRequestByExpectationsId(id);
        for (Request request:requestList){
            template.deleteExpectation(expectationUtils.genExpectation(
                    requestService.getRequestOfCore(request).buildRequest()));
        }
    }

    @Override
    public Expectations getExpectationsById(Integer id) {
        return expectationsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Expectations> getAllExpectationsOfAdmin() {
        ExpectationsExample example = new ExpectationsExample();
        example.createCriteria().andIdIsNotNull();
        return expectationsMapper.selectByExample(example);
    }

    @Override
    public List<Expectations> getAllExpectationsOfAdminByProjectId(Integer projectId) {
        ExpectationsExample example = new ExpectationsExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return expectationsMapper.selectByExample(example);
    }

    @Override
    public List<Expectations> getAllExpectationsOfAdminByProjectId(Integer projectId, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        ExpectationsExample example = new ExpectationsExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return expectationsMapper.selectByExample(example);
    }

    public ExpectationsTemplate getExpectationsOfCore(Expectations expectations){
        ExpectationsTemplate template = new ExpectationsTemplate();
        Optional.ofNullable(expectations.getProjectId()).ifPresent(template::setProjectId);
        Optional.ofNullable(expectations.getId()).ifPresent(template::setExpectationsId);
        template.setServer(bootstrap.getClients().get(expectations.getProjectId()));

        return template;

    }

    @Override
    public List<ExpectationsTemplate> getAllOpenExpectationsOfCore() {
        List<ExpectationsTemplate> templates = new ArrayList<>();
        List<Expectations> expectationsList = getAllExpectationsOfAdmin();
        for (Expectations expectations: expectationsList){
            if (expectations.getIsOpen() == 1){

                ExpectationsTemplate template = new ExpectationsTemplate();
                Optional.ofNullable(expectations.getProjectId()).ifPresent(template::setProjectId);
                Optional.ofNullable(expectations.getId()).ifPresent(template::setExpectationsId);

                templates.add(template);
            }
        }
        return templates;
    }
}
