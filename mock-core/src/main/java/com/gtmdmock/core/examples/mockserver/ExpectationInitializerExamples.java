package com.gtmdmock.core.examples.mockserver;

//import com.demo.mock.mockserver.initializer.ExpectationInitializerExample;
import org.mockserver.integration.ClientAndServer;

public class ExpectationInitializerExamples {

    public void startWithInitializer() {
//        System.setProperty("mockserver.initializationClass", ExpectationInitializerExample.class.getName());
        int mockServerPort = new ClientAndServer().getPort();
        // send requests
    }
}
