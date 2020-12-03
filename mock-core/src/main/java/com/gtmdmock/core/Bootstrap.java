package com.gtmdmock.core;


import com.gtmdmock.core.client.ClientInfo;
import com.gtmdmock.core.client.ClientAction;
import com.gtmdmock.core.client.ServerClient;
import com.gtmdmock.core.expectation.ExpectationsTemplate;
import com.gtmdmock.core.expectation.ExpectationsAction;

import java.util.List;

/**
 * 整个项目的引导器
 */
public class Bootstrap {

    private final ClientAction clientAction = new ClientAction();
    private final ExpectationsAction expectationsAction = new ExpectationsAction();

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

    public ClientAction getClientAction() {
        return clientAction;
    }

    public ExpectationsAction getExpectationsAction() {
        return expectationsAction;
    }

    public void initClients(List<ClientInfo> infos){
        this.clientAction.setClientInfos(infos);
        this.clientAction.clientInstantiate();
    }

    public List<ServerClient> getClients(){
        return this.clientAction.getClients();
    }

    public List<ExpectationsTemplate> getAllExpectations(){
        return this.expectationsAction.getAllExpectations();
    }

    //期望集
    public void initExpectations(List<ExpectationsTemplate> expectationsList){
        expectationsAction.setExpectations(expectationsList);

        List<ServerClient> clients = this.getClients();
        for (ExpectationsTemplate expectations: expectationsList){
            int projectId = expectations.getProjectId();
            for (ServerClient client: clients){
                if (client.getProjectId() == projectId){
                    expectations.setServer(client);
                }
            }
        }
    }







}
