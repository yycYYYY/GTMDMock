package com.gtmdmock.core;

import com.gtmdmock.core.client.ClientInitializer;
import com.gtmdmock.core.client.ServerClient;
import org.junit.Test;

import java.util.List;

public class ClientInitTest {

    static ClientInitializer initializer = new ClientInitializer();

    @Test
    public void test(){


    }

    public static void main(String[] args) {
        List<ServerClient> list = initializer.clientInstantiate();
    }
}
