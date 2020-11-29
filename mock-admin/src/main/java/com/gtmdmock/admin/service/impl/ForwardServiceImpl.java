package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.ForwardExample;
import com.gtmdmock.admin.model.mapper.ForwardMapper;
import com.gtmdmock.admin.service.ForwardService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.forward.ForwardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForwardServiceImpl implements ForwardService {

    @Autowired
    ForwardMapper forwardMapper;

    private final Bootstrap bootstrap = Bootstrap.getInstance();

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

    }

    @Override
    public void updateForwardOfCore(Forward forward) {

    }

    @Override
    public void deleteForwardOfCore(Integer requestId) {

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
