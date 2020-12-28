package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.forward.OverrideForwardTemplate;

import java.util.List;

public interface OverrideForwardService {

    void insertOverrideForward(OverrideForward overrideForward);

    void updateOverrideForward(OverrideForward overrideForward);

    void deleteOverrideForwardById(Integer id);

    void deleteOverrideForwardByRequestId(Integer requestID);

    void insertOverrideForwardToCore(OverrideForward overrideForward);

    void updateOverrideForwardOfCore(OverrideForward overrideForward);

    void deleteOverrideForwardOfCore(Integer requestId);

    OverrideForward getOverrideForwardByRequestId(Integer requestId);

    OverrideForwardTemplate getOverrideForwardOfCore(OverrideForward overrideForward);

    List<OverrideForwardTemplate> getOverrideForwardsOfCore(List<OverrideForward> overrideForwards);
}
