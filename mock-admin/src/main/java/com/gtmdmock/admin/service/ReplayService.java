package com.gtmdmock.admin.service;

public interface ReplayService {

    Boolean replayAll(Integer projectId);

    Boolean replay(Integer projectId,String path);

    Boolean replayAllAndSave(Integer projectId);

    Boolean replayAndSave(Integer projectId,String path);

}
