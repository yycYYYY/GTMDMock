package com.gtmdmock.admin.model.entity;

public class OverrideForward {
    private Integer id;

    private Integer requestId;

    private String host;

    private Integer isSecure;

    private Integer delay;

    private String path;

    private String method;

    private String body;

    private String headers;

    private String cookies;

    private String queryParams;

    private String pathParams;

    private Integer iskeepalive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Integer getIsSecure() {
        return isSecure;
    }

    public void setIsSecure(Integer isSecure) {
        this.isSecure = isSecure;
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
        this.path = path == null ? null : path.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers == null ? null : headers.trim();
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies == null ? null : cookies.trim();
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams == null ? null : queryParams.trim();
    }

    public String getPathParams() {
        return pathParams;
    }

    public void setPathParams(String pathParams) {
        this.pathParams = pathParams == null ? null : pathParams.trim();
    }

    public Integer getIskeepalive() {
        return iskeepalive;
    }

    public void setIskeepalive(Integer iskeepalive) {
        this.iskeepalive = iskeepalive;
    }
}