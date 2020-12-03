package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.core.request.RequestMatcher;
import org.mockserver.mock.Expectation;

import java.util.List;

public interface ExpectationService {

    Expectation getExpectation(RequestMatcher requestMatcher);

    List<Expectations> getExpectationsByProjectId(Integer projectId);
}
