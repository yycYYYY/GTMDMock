package com.gtmdmock.admin.model.entity;

public class Request {
    private Integer id;

    private Integer requestId;

    private Integer expectationsId;

    private String path;

    private String method;

    private String body;

    private Integer issecure;

    private Integer iskeepalive;

    private String headers;

    private String cookies;

    private String queryParams;

    private String pathParams;

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

    public Integer getExpectationsId() {
        return expectationsId;
    }

    public void setExpectationsId(Integer expectationsId) {
        this.expectationsId = expectationsId;
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

    public Integer getIsecure() {
        return issecure;
    }

    public void setIssecure(Integer issecure) {
        this.issecure = issecure;
    }

    public Integer getIskeepalive() {
        return iskeepalive;
    }

    public void setIskeepalive(Integer iskeepalive) {
        this.iskeepalive = iskeepalive;
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
}