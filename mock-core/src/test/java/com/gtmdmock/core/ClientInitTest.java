package com.gtmdmock.core;

import com.gtmdmock.core.client.ClientAction;
import com.gtmdmock.core.client.ServerClient;
import org.junit.Test;

import java.util.List;

public class ClientInitTest {

    static ClientAction initializer = new ClientAction();

    @Test
    public void test(){


    }

    public static void main(String[] args) {
        List<ServerClient> list = initializer.clientInstantiate();
    }
}
