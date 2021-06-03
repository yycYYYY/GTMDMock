package com.gtmdmock.core.handlers;

import org.mockserver.client.MockServerClient;
import org.mockserver.mock.action.ExpectationForwardAndResponseCallback;
import org.mockserver.mock.action.ExpectationForwardCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockserver.model.Header.header;
import static org.mockserver.model.HttpRequest.request;

public class test {

    public static void main(String[] args) {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withPath("/some/path")
                )
                .forward(
                        new ExpectationForwardCallback() {
                            @Override
                            public HttpRequest handle(HttpRequest httpRequest) throws Exception {
                                return request()
                                        .withPath(httpRequest.getPath())
                                        .withMethod("POST")
                                        .withHeaders(
                                                header("x-callback", "test_callback_header"),
                                                header("Content-Length", "a_callback_request".getBytes(UTF_8).length),
                                                header("Connection", "keep-alive")
                                        )
                                        .withBody("a_callback_request");
                            }
                        },
                        new ExpectationForwardAndResponseCallback() {
                            @Override
                            public HttpResponse handle(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
                                return httpResponse
                                        .withHeader("x-response-test", "x-response-test")
                                        .removeHeader(CONTENT_LENGTH.toString())
                                        .withBody("some_overridden_response_body");
                            }
                        }
                );
    }
}
