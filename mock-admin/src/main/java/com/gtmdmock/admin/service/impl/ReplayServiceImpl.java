package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.service.ReplayService;
import com.gtmdmock.core.Bootstrap;
import com.gtmdmock.core.client.ClientAction;
import org.springframework.stereotype.Service;

@Service
public class ReplayServiceImpl implements ReplayService {

    private final ClientAction clientAction = Bootstrap.getInstance().getClientAction();

    @Override
    public Boolean replayAll(Integer projectId) {
        return true;
    }

    @Override
    public Boolean replay(Integer projectId, String path) {
        return true;
    }

    @Override
    public Boolean replayAllAndSave(Integer projectId) {
        return true;
    }

    @Override
    public Boolean replayAndSave(Integer projectId, String path) {
        return true;
    }
}
