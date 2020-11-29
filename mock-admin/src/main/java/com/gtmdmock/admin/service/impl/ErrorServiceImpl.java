package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.service.ErrorService;
import com.gtmdmock.core.httperror.ErrorTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public void insertError() {

    }

    @Override
    public void updateError() {

    }

    @Override
    public void deleteErrorById() {

    }

    @Override
    public void deleteErrorByRequestId() {

    }

    @Override
    public ErrorTemplate getErrorByRequestId(Integer requestId) {
        return null;
    }

    @Override
    public ErrorTemplate getErrorOfCore(ErrorTemplate error) {
        return null;
    }

    @Override
    public List<ErrorTemplate> getErrorsOfCore(List<ErrorTemplate> errors) {
        return null;
    }
}
