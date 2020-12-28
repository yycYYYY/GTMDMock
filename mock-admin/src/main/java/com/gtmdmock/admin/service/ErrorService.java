package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Error;
import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.httperror.ErrorTemplate;

import java.util.List;

public interface ErrorService {

    void insertError(Error error);

    void updateError(Error error);

    void deleteErrorById(Integer id);

    void deleteErrorByRequestId(Integer requestId);

    void insertErrorToCore(Error error);

    void updateErrorOfCore(Error error);

    void deleteErrorOfCore(Integer requestId);

    Error getErrorByRequestId(Integer requestId);

    ErrorTemplate getErrorOfCore(Error error);

    List<ErrorTemplate> getErrorsOfCore(List<Error> errors);
}
