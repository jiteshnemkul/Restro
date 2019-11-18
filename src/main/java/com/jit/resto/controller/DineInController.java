package com.jit.resto.controller;

import com.jit.resto.model.*;
import com.jit.resto.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.xml.transform.sax.SAXSource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class DineInController {
    @Autowired
    private TableService tableService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderTableService orderTableService;


    @Autowired
    private ActiveOrderListService activeOrderList;



    @RequestMapping(value = "/dinein")
    public String index(Model model) {
        List<TableEntity> listoftable=tableService.findAll();
        List<TableEntity> listoftable1=tableService.getFreetable(true);
        List<TableEntity> listoftable2=tableService.getFreetable(false);
        int num=listoftable1.size();
        int reserved=listoftable2.size();
        model.addAttribute("list", listoftable);
        model.addAttribute("freenum",num);
        model.addAttribute("reserved",reserved);
        return "DineIn/list";
    }



    @RequestMapping(value = "/dineincategorylist")
    public String form1(@RequestParam("tablename") String tablename, @RequestParam("seat") String seat, Model model) {
        System.out.println("dhsja");
        Page<CategoryEntity> categoryList = categoryService.getList(1);

        activeOrderList.setActiveOrderList(new HashMap<>());
        activeOrderList.setActiveTable(tablename);

        OrderModel orderModel=new OrderModel();
        orderModel.setOrderentities(activeOrderList.getActiveOrderList().values().stream().collect(Collectors.toList()));
        model.addAttribute("orderList",orderModel);


        model.addAttribute("catlists", categoryList);
        model.addAttribute("tablename",tablename);
        model.addAttribute("seat",seat);

        return "DineIn/form";

    }




    @RequestMapping(value="/addToOrder/{name}", method = RequestMethod.GET)
    public String updateActiveOrder(@PathVariable(value = "name")String itemName,Model model){
        System.out.println("updateActiveOrder called");
        itemName=new String(itemName.replaceAll("-"," "));
        //Orderentity orderentity=activeOrderList.get(itemName);
        Orderentity orderentity=activeOrderList.getActiveOrderList().get(itemName);
        ItemEntity itemEntity=itemService.findByItemName(itemName);
        if(null!=itemEntity) {
            if (null == orderentity) {
                orderentity = new Orderentity();
                orderentity.setItemname(itemEntity.getItemname());
                orderentity.setNumber(1);
                orderentity.setPrice(Integer.parseInt(itemEntity.getPrice()));
                orderentity.setTotal(orderentity.getNumber()*orderentity.getPrice());

            }
            else {
                orderentity.setNumber(orderentity.getNumber()+1);
                orderentity.setTotal(orderentity.getNumber()*orderentity.getPrice());
            }
            activeOrderList.getActiveOrderList().put(itemName,orderentity);
        }
        else {
            System.out.println("Error while fetching item from database");
        }
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderentities(activeOrderList.getActiveOrderList().values());
        model.addAttribute("orderList",orderModel);
        //orderFinalized=false;

        return "DineIn/form::orderlist";
    }

    @RequestMapping(value="/removeFromOrder/{itemName}", method = RequestMethod.GET)
    public String removeFromActiveOrder(@PathVariable(value = "itemName")String itemName,Model model){
        System.out.println("removeFromActiveOrder called");
        itemName=new String(itemName.replaceAll("-"," "));
        activeOrderList.getActiveOrderList().remove(itemName);
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderentities(activeOrderList.getActiveOrderList().values());
        model.addAttribute("orderList",orderModel);
        //orderFinalized=false;
        return "DineIn/form::orderlist";
    }

    @RequestMapping(value="/increaseItemAmount/{itemName}", method = RequestMethod.GET)
    public String increaseItemAmount(@PathVariable(value = "itemName")String itemName,Model model){
        System.out.println("increaseItemAmount called");
        itemName=new String(itemName.replaceAll("-"," "));
        Orderentity orderentity=activeOrderList.getActiveOrderList().get(itemName);
        orderentity.setNumber(orderentity.getNumber()+1);
        orderentity.setTotal(orderentity.getPrice()*orderentity.getNumber());
        activeOrderList.getActiveOrderList().put(itemName,orderentity);
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderentities(activeOrderList.getActiveOrderList().values());
        model.addAttribute("orderList",orderModel);
        //orderFinalized=false;
        return "DineIn/form::orderlist";
    }

    @RequestMapping(value="/decreaseItemAmount/{itemName}", method = RequestMethod.GET)
    public String decreaseItemAmount(@PathVariable(value = "itemName")String itemName,Model model){
        System.out.println("decreaseItemAmount called");
        itemName=new String(itemName.replaceAll("-"," "));
        Orderentity orderentity=activeOrderList.getActiveOrderList().get(itemName);
        if(orderentity.getNumber()>1) {
            orderentity.setNumber(orderentity.getNumber() - 1);
            orderentity.setTotal(orderentity.getPrice() * orderentity.getNumber());
            activeOrderList.getActiveOrderList().put(itemName, orderentity);
            //orderFinalized=false;
        }
        else {
            System.out.println("Order cannot be less than 1");
            //TODO: Display error message
        }
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderentities(activeOrderList.getActiveOrderList().values());
        model.addAttribute("orderList",orderModel);
        return "DineIn/form::orderlist";
    }

    @RequestMapping(value="/calculateTotal", method = RequestMethod.GET)
    public String calculateTotal(Model model){
        System.out.println("calculateTotal called");
        Collection<Orderentity> orderentitys=activeOrderList.getActiveOrderList().values();
        Float total=new Float(0);
        for (Orderentity o:orderentitys){
            total=total+o.getTotal();
        }
        OrderModel orderModel=new OrderModel();
        orderModel.setGrandTotal(total);
        orderModel.setOrderentities(orderentitys);
        activeOrderList.setActiveGrandTotal(total);
        model.addAttribute("orderList",orderModel);
        //orderFinalized=true;
        return "DineIn/form::orderlist";
    }

    @RequestMapping(value="/saveOrder", method = RequestMethod.POST)
    public String saveOrder(){
        System.out.println("saveOrder called");

        Ordertable ordertable=new Ordertable();
        ordertable.setGrandTotal(activeOrderList.getActiveGrandTotal());
        ordertable.setOrderStatus("Ordered");
        ordertable.setTableName(activeOrderList.getActiveTable());
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
        ordertable.setOrderDate(dateFormat.format(date));
        ordertable.setOrderTime(timeFormat.format(date));

        List<Orderentity> orderToSave=new ArrayList<>();
        for(Orderentity o:new ArrayList<>(activeOrderList.getActiveOrderList().values())){
            o.setOrdertable(ordertable);
            o.setOrderStatus("Ordered");
            orderToSave.add(o);
        }

        orderTableService.save(ordertable);
        orderService.saveAll(orderToSave);

        tableService.updateTableStatus(ordertable.getTableName(),false);

        return "redirect:dinein";
    }

    @RequestMapping(value="/enterCategory/{categoryId}",method = RequestMethod.GET)
    public String enterCategory(@PathVariable("categoryId")Long categoryId,Model model){
        System.out.println("enterCategory called");
        List<ItemEntity> page = itemService.findBycategoryid(categoryId);
        model.addAttribute("lists", page);
        return "DineIn/form::menuDisplay";
    }

    @RequestMapping(value="/backToCategory",method = RequestMethod.GET)
    public String backToCategory(Model model){
        System.out.println("backToCategory called");
        Page<CategoryEntity> categoryList = categoryService.getList(1);

        model.addAttribute("catlists", categoryList);
        return "DineIn/form::menuDisplay";
    }

}
