package com.jit.resto.controller;

import com.jit.resto.model.OrderModel;
import com.jit.resto.model.Orderentity;
import com.jit.resto.model.Ordertable;
import com.jit.resto.service.OrderService;
import com.jit.resto.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderProcessController {
    @Autowired
    private OrderTableService orderTableService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/newOrder",method = RequestMethod.GET)
    public String displayNewOrder(Model model){

        List<OrderModel> orderModels=activeOrders();

        model.addAttribute("orders",orderModels);
        return "Orders/newOrder";
    }

    @RequestMapping(value = "/changeCookingStatusOfOrder/{orderId}",method = RequestMethod.GET)
    public String changeCookingStatusOfOrder(Model model, @PathVariable("orderId") Long orderId){
        Ordertable ordertable=orderTableService.get(orderId);
        ordertable.setOrderStatus("Cooked");
        List<Orderentity> orderentities=orderService.findAllByOrderTable(ordertable);

        for(int i=0;i<orderentities.size();i++){
            orderentities.get(i).setOrderStatus("Cooked");
        }

        orderTableService.save(ordertable);
        orderService.saveAll(orderentities);

        List<OrderModel> orderModels=activeOrders();
        model.addAttribute("orders",orderModels);

        return "Orders/newOrder::orderDisplay";
    }

    @RequestMapping(value = "/changeCookingStatusOfItem/{itemId}",method = RequestMethod.GET)
    public String changeCookingStatusOfItem(Model model, @PathVariable("itemId")Long itemId){
        Orderentity orderentity=orderService.get(itemId);
        if(orderentity.getOrderStatus().equalsIgnoreCase("Cooked")){
            orderentity.setOrderStatus("Ordered");
            orderService.save(orderentity);
        }
        else {
            orderentity.setOrderStatus("Cooked");
            orderService.save(orderentity);

            List<Orderentity> orderentityList = orderService.findAllByOrderStatusAndOrderTable("Ordered", orderentity.getOrdertable());
            if (orderentityList.isEmpty()) {
                Ordertable ordertable = orderentity.getOrdertable();
                ordertable.setOrderStatus("Cooked");
                orderTableService.save(ordertable);
            }
        }

        List<OrderModel> orderModels=activeOrders();
        model.addAttribute("orders",orderModels);

        return "Orders/newOrder::orderDisplay";
    }

    @RequestMapping(value = "/refreshActiveOrders",method = RequestMethod.GET)
    public String refreshActiveOrders(Model model){
        List<OrderModel> orderModels=activeOrders();

        model.addAttribute("orders",orderModels);
        return "Orders/newOrder::orderDisplay";
    }

    private List<OrderModel> activeOrders(){
        List<Ordertable> ordertables=orderTableService.findAllBtOrderStatus("Ordered");
        List<OrderModel> orderModels=new ArrayList<>();
        for(Ordertable o:ordertables){
            OrderModel orderModel=new OrderModel();
            orderModel.setOrderId(o.getId());
            orderModel.setTableName(o.getTableName());
            orderModel.setOrderTime(o.getOrderTime());
            orderModel.setRowIndexForNewOrderDisplay((ordertables.indexOf(o)/4));
            orderModel.setOrderentities(orderService.findAllByOrderTable(o));
            orderModels.add(orderModel);
        }
        return orderModels;
    }

}
