package com.gtmdmock.admin.service.impl;

import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.admin.model.entity.OverrideForwardExample;
import com.gtmdmock.admin.model.mapper.OverrideForwardMapper;
import com.gtmdmock.admin.service.OverrideForwardService;
import com.gtmdmock.admin.utils.JsonUtils;
import com.gtmdmock.core.forward.OverrideForwardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OverrideForwardServiceImpl implements OverrideForwardService {

    @Autowired
    OverrideForwardMapper overrideForwardMapper;

    @Override
    public OverrideForward getOverrideForwardByRequestId(Integer overrideForwardId) {
        OverrideForwardExample example = new OverrideForwardExample();
        example.createCriteria().andRequestIdEqualTo(overrideForwardId);
        return overrideForwardMapper.selectByExample(example).get(0);
    }

    @Override
    public OverrideForwardTemplate getOverrideForwardOfCore(OverrideForward overrideForward) {
        OverrideForwardTemplate template = new OverrideForwardTemplate();

        Optional.ofNullable(overrideForward.getPath()).ifPresent(template:: setPath);
        Optional.ofNullable(overrideForward.getHost()).ifPresent(template:: setHost);
        Optional.ofNullable(overrideForward.getBody()).ifPresent(template:: setBody);
        Optional.ofNullable(overrideForward.getDelay()).ifPresent(template:: setDelay);
        Optional.ofNullable(overrideForward.getRequestId()).ifPresent(template:: setRequestId);
        Optional.ofNullable(overrideForward.getMethod()).ifPresent(template:: setMethod);

        if (overrideForward.getHeaders() != null && !overrideForward.getHeaders().equals("")){
            template.setHeaders(JsonUtils.StringToMap(overrideForward.getHeaders()));
        }

        if (overrideForward.getCookies() != null && !overrideForward.getCookies().equals("")){
            template.setCookies(JsonUtils.StringToMap(overrideForward.getCookies()));
        }

        if (overrideForward.getPathParams() != null && !overrideForward.getPathParams().equals("")){
            template.setPathParams(JsonUtils.StringToMap(overrideForward.getPathParams()));
        }

        if (overrideForward.getQueryParams() != null && !overrideForward.getQueryParams().equals("")){
            template.setQueryParams(JsonUtils.StringToMap(overrideForward.getQueryParams()));
        }

        if (overrideForward.getIskeepalive() != null){
            template.setKeepAlive(overrideForward.getIskeepalive() == 1);
        }

        if (overrideForward.getIsSecure() != null){
            template.setSecure(overrideForward.getIsSecure() == 1);
        }
        return template;
    }

    @Override
    public List<OverrideForwardTemplate> getOverrideForwardsOfCore(List<OverrideForward> overrideForwards) {
        return null;
    }
}
