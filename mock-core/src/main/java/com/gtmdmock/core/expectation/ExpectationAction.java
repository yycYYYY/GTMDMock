package com.gtmdmock.core.expectation;

import org.mockserver.mock.Expectation;
import org.mockserver.model.*;

import java.util.Optional;

public class ExpectationAction {

    //改变期望,改变期望，用这个类重新生成一个期望，然后client upsert一下

    //删除期望，直接让client clear一下

    public Expectation genExpectation(HttpRequest request, HttpResponse response, HttpForward forward, HttpOverrideForwardedRequest overrideForwarded, HttpError error){
        Expectation expectation = new Expectation(request);
        Optional.ofNullable(response).ifPresent(expectation::thenRespond);
        Optional.ofNullable(forward).ifPresent(expectation::thenForward);
        Optional.ofNullable(overrideForwarded).ifPresent(expectation::thenForward);
        Optional.ofNullable(error).ifPresent(expectation::thenError);
        return expectation;
    }

    public Expectation genExpectation(HttpRequest request, HttpResponse response){
        return this.genExpectation(request,response,(HttpForward)null,(HttpOverrideForwardedRequest)null,(HttpError)null);
    }

    public Expectation genExpectation(HttpRequest request, HttpForward forward){
        return this.genExpectation(request,(HttpResponse) null,forward,(HttpOverrideForwardedRequest)null,(HttpError) null);
    }

    public Expectation genExpectation(HttpRequest request, HttpOverrideForwardedRequest overrideForwarded){
        return this.genExpectation(request,(HttpResponse) null,(HttpForward)null,overrideForwarded,(HttpError) null);
    }

    public Expectation genExpectation(HttpRequest request, HttpError error){
        return this.genExpectation(request,(HttpResponse) null,(HttpForward)null,(HttpOverrideForwardedRequest)null,error);
    }

    public Expectation genExpectation(HttpRequest request){
        return this.genExpectation(request,(HttpError)null);
    }

}
