package com.gtmdmock.core.response;

import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ResponseTemplate {

    private String requestId;
    private String body;
    private Integer statusCode;
    private MediaType contentType;
    private Map<String,String> headers;
    private Map<String,String> cookies;
    private Integer delay;//以s计数

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public MediaType getContentType() {
        return contentType;
    }

    public void setContentType(MediaType contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public HttpResponse buildResponse(){
        HttpResponse response = new HttpResponse();
        Optional.ofNullable(body).ifPresent(response::withBody);
        Optional.ofNullable(statusCode).ifPresent(response::withStatusCode);
        Optional.ofNullable(contentType).ifPresent(response::withContentType);

        if (delay != null){
            response.withDelay(TimeUnit.SECONDS,delay);
        }

        if (headers != null){
            for (Map.Entry<String,String > entry: this.headers.entrySet()){
                response.withHeader(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
            }
        }

        if (cookies != null){
            for (Map.Entry<String,String > entry: this.cookies.entrySet()){
                response.withCookie(entry.getKey(),entry.getValue());
            }
        }


        return response;
    }
}
