package com.jit.resto.service;

import com.jit.resto.model.Orderentity;
import com.jit.resto.model.TableEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActiveOrderListService {
    private Map<String, Orderentity> activeOrderList;
    private String activeTable;
    private Float activeGrandTotal;

    public Map<String, Orderentity> getActiveOrderList() {
        return activeOrderList;
    }

    public void setActiveOrderList(Map<String, Orderentity> activeOrderList) {
        this.activeOrderList = activeOrderList;
    }

    public String getActiveTable() {
        return activeTable;
    }

    public void setActiveTable(String activeTable) {
        this.activeTable = activeTable;
    }

    public Float getActiveGrandTotal() {
        return activeGrandTotal;
    }

    public void setActiveGrandTotal(Float activeGrandTotal) {
        this.activeGrandTotal = activeGrandTotal;
    }
}
