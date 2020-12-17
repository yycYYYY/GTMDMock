package com.gtmdmock.core.request;

import org.mockserver.model.HttpRequest;

import java.util.Map;
import java.util.Optional;

public class RequestMatcher {
    private Integer requestId;
    private Integer expectationId;
    private String matcherType;//response、error、forward、overrideForward
    private String verifyType;
    private String path;
    private String method;
    private String body;
    private boolean isSecure;
    private boolean isKeepAlive;
    private Map<String,String> headers;
    private Map<String,String> cookies;
    private Map<String,String> queryParams;
    private Map<String,String> pathParams;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getExpectationId() {
        return expectationId;
    }

    public void setExpectationId(Integer expectationId) {
        this.expectationId = expectationId;
    }

    public String getMatcherType() {
        return matcherType;
    }

    public void setMatcherType(String matcherType) {
        this.matcherType = matcherType;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public void setSecure(boolean secure) {
        isSecure = secure;
    }

    public boolean isKeepAlive() {
        return isKeepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        isKeepAlive = keepAlive;
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

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }

    public HttpRequest buildRequest(){
        HttpRequest request = new HttpRequest();
        Optional.ofNullable(this.path).ifPresent(request::withPath);
        Optional.ofNullable(this.method).ifPresent(request::withMethod);
        Optional.ofNullable(this.body).ifPresent(request::withBody);
        Optional.of(this.isSecure).ifPresent(request::withSecure);
        Optional.of(this.isKeepAlive).ifPresent(request::withKeepAlive);

        if (this.headers != null){
            for (Map.Entry<String,String> entry: this.headers.entrySet()){
                request.withHeader(entry.getKey(),entry.getValue());
            }
        }

        if (this.cookies != null){
            for (Map.Entry<String,String> entry: this.cookies.entrySet()){
                request.withCookie(entry.getKey(),entry.getValue());
            }
        }

        if (this.queryParams != null){
            for (Map.Entry<String,String> entry: this.queryParams.entrySet()){
                request.withQueryStringParameter(entry.getKey(),entry.getValue());
            }
        }

        if (this.pathParams != null){
            for (Map.Entry<String,String> entry: this.pathParams.entrySet()){
                request.withPathParameter(entry.getKey(),entry.getValue());
            }
        }

        //TODO 这里后续还需要对headers和cookies的验证的补全，以及对has，not判断类型的补全
        return request;
    }

}
