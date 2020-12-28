package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.ForwardExample;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.mapper.ForwardMapper;
import com.gtmdmock.admin.service.ForwardService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.expectation.ExpectationAction;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.request.RequestMatcher;
import org.mockserver.mock.Expectation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForwardServiceImpl implements ForwardService {

    private final Logger logger = LoggerFactory.getLogger(ForwardServiceImpl.class);

    @Autowired
    ForwardMapper forwardMapper;

    @Autowired
    RequestService requestService;

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private final ExpectationsAction expectationsAction = bootstrap.getExpectationsAction();

    private final ExpectationAction expectationUtils = new ExpectationAction();

    @Override
    public void insertForward(Forward forward) {
        forwardMapper.insert(forward);
    }

    @Override
    public void updateForward(Forward forward) {
        forwardMapper.updateByPrimaryKey(forward);
    }

    @Override
    public void deleteForwardById(Integer id) {
        forwardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteForwardByRequestId(Integer requestId) {
        ForwardExample example = new ForwardExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        forwardMapper.deleteByExample(example);
    }

    @Override
    public void insertForwardToCore(Forward forward) {
        this.insertForward(forward);
        Request request = requestService.getRequestById(forward.getRequestId());
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest(),getForwardOfCore(forward).buildForward());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        logger.info("在{}期望集下requestId为{}的forward",request.getExpectationsId(),request.getId());

        template.updateExpectation(expectation);
    }

    @Override
    public void updateForwardOfCore(Forward forward) {
        this.updateForward(forward);
        Request request = requestService.getRequestById(forward.getRequestId());
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest(),getForwardOfCore(forward).buildForward());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        logger.info("在{}期望集下requestId为{}的forward",request.getExpectationsId(),request.getId());

        template.updateExpectation(expectation);
    }

    @Override
    public void deleteForwardOfCore(Integer requestId) {
        this.deleteForwardByRequestId(requestId);
        Request request = requestService.getRequestById(requestId);
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());

        template.updateExpectation(expectation);
    }

    @Override
    public Forward getForwardByRequestId(Integer requestId) {
        ForwardExample example = new ForwardExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        return forwardMapper.selectByExample(example).get(0);
    }

    @Override
    public ForwardTemplate getForwardOfCore(Forward forward) {
        ForwardTemplate forwardTemplate = new ForwardTemplate();

        Optional.ofNullable(forward.getRequestId()).ifPresent(forwardTemplate::setRequestId);
        Optional.ofNullable(forward.getHost()).ifPresent(forwardTemplate::setHost);
        Optional.ofNullable(forward.getPort()).ifPresent(forwardTemplate::setPort);
        Optional.ofNullable(forward.getDelay()).ifPresent(forwardTemplate::setDelay);

        if (Optional.ofNullable(forward.getIsSecure()).isPresent()){
            forwardTemplate.setSecure(forward.getIsSecure() == 1);
        }
        return forwardTemplate;
    }

    @Override
    public List<ForwardTemplate> getForwardsOfCore(List<Forward> forwards) {
        List<ForwardTemplate> templates = new ArrayList<>();

        for (Forward forward: forwards){
            templates.add(getForwardOfCore(forward));
        }

        return templates;
    }
}
