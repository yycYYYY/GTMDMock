package com.gtmdmock.core.response;

import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;

import java.util.Map;

public class ResponseTemplate extends HttpResponse {
    String requestId;
    String body;
    int statusCode;
    MediaType contentType;
    Map<String,String> headers;
    Map<String,String> cookies;
    int delay;//以s计数

}
