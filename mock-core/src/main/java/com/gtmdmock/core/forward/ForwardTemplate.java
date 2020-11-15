package com.gtmdmock.core.forward;

import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpOverrideForwardedRequest;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ForwardTemplate {
    private String requestId;
    //TODO:这里还需要做请求转发重写，HttpOverrideForwardedRequest的支持，目前未实现
    private String forwardType;
    private String host;
    private Integer port;
    private boolean isSecure;
    private Integer delay;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getForwardType() {
        return forwardType;
    }

    public void setForwardType(String forwardType) {
        this.forwardType = forwardType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public void setSecure(boolean secure) {
        isSecure = secure;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public HttpForward buildForeard(){
        HttpForward forward = new HttpForward();
        Optional.ofNullable(this.host).ifPresent(forward::withHost);
        Optional.ofNullable(this.port).ifPresent(forward::withPort);
        if (isSecure){forward.withScheme(HttpForward.Scheme.HTTPS);}else {forward.withScheme(HttpForward.Scheme.HTTP);}
        if (delay != null){
            forward.withDelay(TimeUnit.SECONDS,delay);
        }

        return forward;
    }

}
