package com.gtmdmock.core.expectation;

import com.gtmdmock.core.handlers.ResponseOverrideCallback;
import org.mockserver.mock.Expectation;
import org.mockserver.model.*;

import java.util.Optional;

/**
 * 期望生成类：用于生成期望
 */
public class ExpectationGenerator {

    public Expectation genExpectation(HttpRequest request, HttpResponse response, HttpForward forward, HttpOverrideForwardedRequest overrideForwarded, HttpError error){
        Expectation expectation = new Expectation(request);
        if (Optional.ofNullable(response).isPresent()){
            expectation.thenRespond(response);
        }else {
            expectation.thenRespond(HttpResponse.response().withBody("null"));
        }
//        if (Optional.ofNullable(response).isPresent(forward)){
//            expectation.thenForward(forward, new ResponseOverrideCallback());
//        }
        Optional.ofNullable(forward).ifPresent(expectation::thenForward);
        Optional.ofNullable(overrideForwarded).ifPresent(expectation::thenForward);
        Optional.ofNullable(error).ifPresent(expectation::thenError);
        return expectation;
    }

    public Expectation genExpectation(HttpRequest request, HttpResponse response){
        return this.genExpectation(request,response,null,null,null);
    }

    public Expectation genExpectation(HttpRequest request, HttpForward forward){
        return this.genExpectation(request,null,forward,null, null);
    }

    public Expectation genExpectation(HttpRequest request, HttpOverrideForwardedRequest overrideForwarded){
        return this.genExpectation(request, null,null,overrideForwarded, null);
    }

    public Expectation genExpectation(HttpRequest request, HttpError error){
        return this.genExpectation(request, null,null,null,error);
    }

    public Expectation genExpectation(HttpRequest request){
        return this.genExpectation(request,(HttpError)null);
    }

}
