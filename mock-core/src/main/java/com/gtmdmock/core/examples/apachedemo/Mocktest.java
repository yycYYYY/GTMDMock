package com.gtmdmock.core.examples.apachedemo;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.model.ClearType;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpOverrideForwardedRequest;
import org.mockserver.model.HttpRequest;
import org.mockserver.netty.MockServer;

import java.util.concurrent.TimeUnit;

import static org.mockserver.model.Cookie.cookie;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;

public class Mocktest {
    public static void main(String[] args) {
        ClientAndServer server = new ClientAndServer(1080);
//        ClientAndServer server1 = new ClientAndServer("hq-dev.com",443,1081);
//        ClientAndServer server2 = new ClientAndServer(1082);
        ExpectationInitializerPublic initializer = new ExpectationInitializerPublic();

        initializer.initializeExpectations(server);

//        addExpectation(server);

//        server.stopAsync();
//        server.when(
//                request()
//        );

    }

    private static void recorderTest(){
        Expectation[] recordedExpectations = new MockServerClient("localhost", 1080)
                .retrieveRecordedExpectations(
                        request()
                                .withPath("/test")
                );

        for (Expectation e: recordedExpectations){
            System.out.println(e.getAction());
        }
    }


    private static void registerMockServerExpectationListener() {

        MockServer mockServer = new MockServer().registerListener(expectations -> {
            System.out.println("MockServer expectations updated " + expectations);
        });
        MockServerClient client = new MockServerClient("localhost", mockServer.getLocalPort());

                client.when(
                        request()
                                .withMethod("GET")
                                .withPath("/view/cart")
                                .withCookies(
                                        cookie("session", "4930456C-C718-476F-971F-CB8E047AB349")
                                )
                                .withQueryStringParameters(
                                        param("cartId", "055CA455-1DF7-45BB-8535-4F83E7266092")
                                )
                )
                .respond(
                        response()
                                .withBody("some_response_body")
                );

        Expectation[] recordedExpectations = client.retrieveRecordedExpectations(
                        request()
                );


        HttpRequest r = request().withPath("/view/cart");


        client.clear(r, ClearType.LOG);

        System.out.println(recordedExpectations.length);
        System.out.println(mockServer.isRunning());
    }

    private static void addExpectation(ClientAndServer server){
        Expectation expectation = new Expectation(
                request().withPath("addE")

        ).withPriority(1).thenRespond(
                response().withBody("addExpectationSuccess")
        ).thenForward(
                HttpOverrideForwardedRequest.forwardOverriddenRequest(request())
        );

        server.upsert(expectation);
        System.out.println("insert success");
        server.clear(expectation.getHttpRequest(),ClearType.EXPECTATIONS);
        System.out.println("clear success");
    }

    private static Expectation[] getAllExpectations(ClientAndServer server){
        return server.when(request().withPath("/getAllExpectations")).respond(response().withBody("a"));
    }
}
