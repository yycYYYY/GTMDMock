package com.gtmdmock.core;


import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.client.ClientAction;
import com.gtmdmock.core.client.ServerClient;
import com.gtmdmock.core.expectation.ExpectationsAction;

import java.util.List;

/**
 * 整个项目的引导器
 */
public class Bootstrap {

    private static volatile Bootstrap instance = null;

    private Bootstrap() {}

    public static Bootstrap getInstance() {
        if (instance == null) {
            synchronized (Bootstrap.class) {
                if (instance == null) {
                    instance = new Bootstrap();
                }
            }
        }
        return instance;
    }

    private final ClientAction clientAction = new ClientAction();
    private final ExpectationsAction expectationsAction = new ExpectationsAction();

    public void initClients(List<ClientInfo> infos){
        this.clientAction.setClientInfos(infos);
        this.clientAction.clientInstantiate();
    }

    public List<ServerClient> getClients(){
        return this.clientAction.getClients();
    }







}
