package com.gtmdmock.core.handlers;

import org.mockserver.mock.action.ExpectationForwardAndResponseCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

public class ResponseOverrideCallback implements ExpectationForwardAndResponseCallback {

    @Override
    public HttpResponse handle(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        if (502 == httpResponse.getStatusCode()){

        }
        return httpResponse;
    }

}
