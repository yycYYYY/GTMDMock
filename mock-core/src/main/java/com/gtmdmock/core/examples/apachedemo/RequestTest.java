package com.gtmdmock.core.examples.apachedemo;

import org.mockserver.mock.Expectation;
import org.mockserver.model.*;

public class RequestTest {


    void test(){
        HttpRequest request = HttpRequest.request().withPath("/");

    }

    void responseTest(){
        HttpResponse response = HttpResponse.response().withBody("");
        HttpForward forward = HttpForward.forward();
    }
}
