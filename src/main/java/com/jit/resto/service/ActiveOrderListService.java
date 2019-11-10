package com.jit.resto.service;

import com.jit.resto.model.Orderentity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActiveOrderListService {
    private Map<String, Orderentity> activeOrderList;

    public Map<String, Orderentity> getActiveOrderList() {
        return activeOrderList;
    }

    public void setActiveOrderList(Map<String, Orderentity> activeOrderList) {
        this.activeOrderList = activeOrderList;
    }
}
