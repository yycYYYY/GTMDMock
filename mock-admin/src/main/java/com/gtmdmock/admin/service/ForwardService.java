package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.core.forward.ForwardTemplate;

import java.util.List;

public interface ForwardService {

    Forward getForwardByRequestId(Integer requestId);

    ForwardTemplate getForwardOfCore(Forward forward);

    List<ForwardTemplate> getForwardsOfCore(List<Forward> forwards);
}
