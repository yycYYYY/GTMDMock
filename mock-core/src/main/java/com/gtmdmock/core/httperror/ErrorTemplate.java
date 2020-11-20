package com.gtmdmock.core.httperror;

import org.mockserver.model.HttpError;
import java.util.concurrent.TimeUnit;

public class ErrorTemplate {
    private String requestId;
    private String response;
    private boolean isDropConnection;
    private Integer delay;

    public HttpError buildError(){
        HttpError error = new HttpError();
        if (this.response != null){
            error.withResponseBytes(this.response.getBytes());
        }
        error.withDropConnection(this.isDropConnection);
        if (this.delay != null){
            error.withDelay(TimeUnit.SECONDS,delay);
        }

        return error;
    }
}
