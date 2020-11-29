package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.core.forward.ForwardTemplate;

import java.util.List;

public interface ForwardService {

    void insertForward(Forward forward);

    void updateForward(Forward forward);

    void deleteForwardById(Integer id);

    void deleteForwardByRequestId(Integer requestId);

    void insertForwardToCore(Forward forward);

    void updateForwardOfCore(Forward forward);

    void deleteForwardOfCore(Integer requestId);

    Forward getForwardByRequestId(Integer requestId);

    ForwardTemplate getForwardOfCore(Forward forward);

    List<ForwardTemplate> getForwardsOfCore(List<Forward> forwards);
}
