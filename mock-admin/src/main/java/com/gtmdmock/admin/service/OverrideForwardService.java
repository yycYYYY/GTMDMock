package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.forward.OverrideForwardTemplate;

import java.util.List;

public interface OverrideForwardService {

    OverrideForward getOverrideForwardByRequestId(Integer requestId);

    OverrideForwardTemplate getOverrideForwardOfCore(OverrideForward overrideForward);

    List<OverrideForwardTemplate> getOverrideForwardsOfCore(List<OverrideForward> overrideForwards);
}
