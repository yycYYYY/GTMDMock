package com.gtmdmock.core.request;

import org.mockserver.model.HttpRequest;

import java.util.Map;
import java.util.Optional;

public class RequestMatcher {
    String expectationId;
    String matcherType;
    String verifyType;
    String path;
    String method;
    String body;
    boolean isSecure;
    boolean isKeepAlive;
    Map<String,String> headers;
    Map<String,String> cookies;
    Map<String,String> queryParams;
    Map<String,String> pathParams;

    public HttpRequest buildRequest(){
        HttpRequest request = new HttpRequest();
        Optional.ofNullable(this.path).ifPresent(request::withPath);
        Optional.ofNullable(this.method).ifPresent(request::withMethod);
        Optional.ofNullable(this.body).ifPresent(request::withBody);
        Optional.ofNullable(this.isSecure).ifPresent(request::withSecure);
        Optional.ofNullable(this.isKeepAlive).ifPresent(request::withKeepAlive);

//        @TODO 这里还需要对headers和cookies的验证的补全，已经对has，not判断类型的补全
        return request;
    }

}
