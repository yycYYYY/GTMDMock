package com.gtmdmock.admin.service;

import com.gtmdmock.core.request.RequestMatcher;
import org.mockserver.mock.Expectation;

public interface ExpectationService {

    Expectation getExpectation(RequestMatcher requestMatcher);
}
