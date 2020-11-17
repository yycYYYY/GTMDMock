package com.gtmdmock.core.expectation;

import org.mockserver.mock.Expectation;
import org.mockserver.model.*;

import java.util.Optional;

public class ExpectationInitializer {

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
