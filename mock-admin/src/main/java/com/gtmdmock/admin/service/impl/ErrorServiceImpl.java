package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.Error;
import com.gtmdmock.admin.model.entity.ErrorExample;
import com.gtmdmock.admin.model.mapper.ErrorMapper;
import com.gtmdmock.admin.service.ErrorService;
import com.gtmdmock.core.httperror.ErrorTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Autowired
    ErrorMapper errorMapper;


    @Override
    public void insertError(Error error) {
        errorMapper.insert(error);
    }

    @Override
    public void updateError(Error error) {
        errorMapper.updateByPrimaryKey(error);
    }

    @Override
    public void deleteErrorById(Integer id) {
        errorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteErrorByRequestId(Integer requestId) {
        ErrorExample example = new ErrorExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        errorMapper.deleteByExample(example);
    }

    @Override
    public Error getErrorByRequestId(Integer requestId) {
        ErrorExample example = new ErrorExample();
        example.createCriteria().andRequestIdEqualTo(requestId);
        return errorMapper.selectByExample(example).get(0);
    }

    @Override
    public ErrorTemplate getErrorOfCore(Error error) {
        ErrorTemplate template = new ErrorTemplate();
        Optional.ofNullable(error.getRequestId()).ifPresent(template:: setRequestId);
        Optional.of(error.getDelay()).ifPresent(template:: setDelay);
        Optional.ofNullable(error.getResponse()).ifPresent(template::setResponse);
        if (Optional.ofNullable(error.getIsDropConnection()).isPresent()){
            template.setDropConnection(error.getIsDropConnection() == 1);
        }
        return template;
    }

    @Override
    public List<ErrorTemplate> getErrorsOfCore(List<Error> errors) {
        List<ErrorTemplate> templates = new ArrayList<>();
        for (Error error: errors){
            templates.add(getErrorOfCore(error));
        }
        return templates;
    }
}
