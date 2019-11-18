package com.jit.resto.controller;

import com.jit.resto.service.OrderService;
import com.jit.resto.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderTableService orderTableService;


}
