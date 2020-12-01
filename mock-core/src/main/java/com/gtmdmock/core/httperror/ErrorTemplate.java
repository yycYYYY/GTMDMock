package com.gtmdmock.core.httperror;

import org.mockserver.model.HttpError;
import java.util.concurrent.TimeUnit;

public class ErrorTemplate {
    private Integer requestId;
    private String response;
    private boolean isDropConnection;
    private Integer delay;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isDropConnection() {
        return isDropConnection;
    }

    public void setDropConnection(boolean dropConnection) {
        isDropConnection = dropConnection;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

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
