package com.gtmdmock.core.forward;

import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpOverrideForwardedRequest;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.SocketAddress;

import java.util.Map;
import java.util.Optional;

public class OverrideForwardTemplate {
    private String requestId;
    private String host;
    private Integer port;
    private boolean isSecure;
    private Integer delay;
    private String path;
    private String method;
    private String body;
    private Map<String,String> headers;
    private Map<String,String> cookies;
    private Map<String,String> queryParams;
    private Map<String,String> pathParams;
    private boolean isKeepAlive;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public void setSecure(boolean secure) {
        isSecure = secure;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
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

    public boolean isKeepAlive() {
        return isKeepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        isKeepAlive = keepAlive;
    }

    public HttpOverrideForwardedRequest buildOverrideForward(){
        HttpOverrideForwardedRequest forwardedRequest = new HttpOverrideForwardedRequest();
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

        request.withSocketAddress(this.host,this.port,isSecure?SocketAddress.Scheme.HTTPS:SocketAddress.Scheme.HTTP);

        forwardedRequest.withHttpRequest(request);
        return forwardedRequest;
    }
}
