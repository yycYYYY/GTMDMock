package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.admin.model.entity.OverrideForwardExample;
import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.mapper.OverrideForwardMapper;
import com.gtmdmock.admin.service.OverrideForwardService;
import com.gtmdmock.admin.service.RequestService;
import com.gtmdmock.admin.utils.JsonUtils;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.expectation.ExpectationAction;
import com.gtmdmock.core.expectation.ExpectationsAction;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.forward.OverrideForwardTemplate;
import com.gtmdmock.core.request.RequestMatcher;
import org.mockserver.mock.Expectation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OverrideForwardServiceImpl implements OverrideForwardService {

    private final Logger logger = LoggerFactory.getLogger(OverrideForwardServiceImpl.class);

    @Autowired
    OverrideForwardMapper overrideForwardMapper;

    @Autowired
    RequestService requestService;

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private final ExpectationsAction expectationsAction = bootstrap.getExpectationsAction();

    private final ExpectationAction expectationUtils = new ExpectationAction();

    @Override
    public void insertOverrideForward(OverrideForward overrideForward) {
        overrideForwardMapper.insert(overrideForward);
    }

    @Override
    public void updateOverrideForward(OverrideForward overrideForward) {
        overrideForwardMapper.updateByPrimaryKey(overrideForward);
    }

    @Override
    public void deleteOverrideForwardById(Integer id) {
        overrideForwardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteOverrideForwardByRequestId(Integer requestID) {
        OverrideForwardExample example = new OverrideForwardExample();
        example.createCriteria().andRequestIdEqualTo(requestID);
        overrideForwardMapper.deleteByExample(example);
    }

    @Override
    public void insertOverrideForwardToCore(OverrideForward overrideForward) {

        this.insertOverrideForward(overrideForward);

        Request request = requestService.getRequestById(overrideForward.getRequestId());
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest(),getOverrideForwardOfCore(overrideForward).buildOverrideForward());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        logger.info("在{}期望集下requestId为{}的overrideForward",request.getExpectationsId(),request.getId());

        template.updateExpectation(expectation);

    }

    @Override
    public void updateOverrideForwardOfCore(OverrideForward overrideForward) {

        this.updateOverrideForward(overrideForward);

        Request request = requestService.getRequestById(overrideForward.getRequestId());
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest(),getOverrideForwardOfCore(overrideForward).buildOverrideForward());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());
        logger.info("在{}期望集下requestId为{}的overrideForward",request.getExpectationsId(),request.getId());

        template.updateExpectation(expectation);
    }

    @Override
    public void deleteOverrideForwardOfCore(Integer requestId) {
        this.deleteOverrideForwardByRequestId(requestId);
        Request request = requestService.getRequestById(requestId);
        RequestMatcher requestMatcher = requestService.getRequestOfCore(request);

        Expectation expectation = expectationUtils.genExpectation(requestMatcher.buildRequest());
        ExpectationsTemplate template = expectationsAction.getExpectationTemplate(request.getExpectationsId());

        template.updateExpectation(expectation);
    }

    @Override
    public OverrideForward getOverrideForwardByRequestId(Integer overrideForwardId) {
        OverrideForwardExample example = new OverrideForwardExample();
        example.createCriteria().andRequestIdEqualTo(overrideForwardId);
        return overrideForwardMapper.selectByExample(example).get(0);
    }

    @Override
    public OverrideForwardTemplate getOverrideForwardOfCore(OverrideForward overrideForward) {
        OverrideForwardTemplate template = new OverrideForwardTemplate();

        Optional.ofNullable(overrideForward.getPath()).ifPresent(template:: setPath);
        Optional.ofNullable(overrideForward.getHost()).ifPresent(template:: setHost);
        Optional.ofNullable(overrideForward.getBody()).ifPresent(template:: setBody);
        Optional.ofNullable(overrideForward.getDelay()).ifPresent(template:: setDelay);
        Optional.ofNullable(overrideForward.getRequestId()).ifPresent(template:: setRequestId);
        Optional.ofNullable(overrideForward.getMethod()).ifPresent(template:: setMethod);

        if (overrideForward.getHeaders() != null && !overrideForward.getHeaders().equals("")){
            template.setHeaders(JsonUtils.StringToMap(overrideForward.getHeaders()));
        }

        if (overrideForward.getCookies() != null && !overrideForward.getCookies().equals("")){
            template.setCookies(JsonUtils.StringToMap(overrideForward.getCookies()));
        }

        if (overrideForward.getPathParams() != null && !overrideForward.getPathParams().equals("")){
            template.setPathParams(JsonUtils.StringToMap(overrideForward.getPathParams()));
        }

        if (overrideForward.getQueryParams() != null && !overrideForward.getQueryParams().equals("")){
            template.setQueryParams(JsonUtils.StringToMap(overrideForward.getQueryParams()));
        }

        if (overrideForward.getIsKeepAlive() != null){
            template.setKeepAlive(overrideForward.getIsKeepAlive() == 1);
        }

        if (overrideForward.getIsSecure() != null){
            template.setSecure(overrideForward.getIsSecure() == 1);
        }
        return template;
    }

    @Override
    public List<OverrideForwardTemplate> getOverrideForwardsOfCore(List<OverrideForward> overrideForwards) {
        return null;
    }
}
