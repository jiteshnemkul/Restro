package com.jit.resto.controller;

import com.jit.resto.model.*;
import com.jit.resto.service.ActiveOrderListService;
import com.jit.resto.service.CategoryService;
import com.jit.resto.service.ItemService;
import com.jit.resto.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.xml.transform.sax.SAXSource;
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


    /*@Autowired
    private Map<String,Orderentity> activeOrderList;*/

    @Autowired
    private ActiveOrderListService activeOrderList;

    //private Boolean orderFinalized=false;

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


    /*@RequestMapping(value = "/dinein/form")
    public String form(@RequestParam("tablename") String tablename, @RequestParam("seat") String seat,Model model){
        System.out.println("dhsja");
        model.addAttribute("tablename",tablename);
        model.addAttribute("seat",seat);
        return "DineIn/form";
    }*/

    /*@RequestMapping(value = "/dineinlist")
    public String index() {
        return "redirect:/category/1";
    }*/
    @RequestMapping(value = "/dineincategorylist")
    public String form1(@RequestParam("tablename") String tablename, @RequestParam("seat") String seat, Model model) {
        System.out.println("dhsja");
        Page<CategoryEntity> page = categoryService.getList(1);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("catlists", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("tablename",tablename);
        model.addAttribute("seat",seat);

        return "DineIn/categorylist";

    }


    @RequestMapping(value = "/dineinlist")
    public String form(@RequestParam("tablename") String tablename, @RequestParam("seat") String seat, Model model) {
        System.out.println("dhsja");
        Page<ItemEntity> page = itemService.getList(1);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("lists", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("tablename",tablename);
        model.addAttribute("seat",seat);

        //activeOrderList=new HashMap<>();
        activeOrderList.setActiveOrderList(new HashMap<>());
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderentities(activeOrderList.getActiveOrderList().values().stream().collect(Collectors.toList()));
        //model.addAttribute("orderList",activeOrderList.values());
        model.addAttribute("orderList",orderModel);
        //orderFinalized=false;
        return "DineIn/form";

    }

    @RequestMapping(value="/addToOrder/{name}", method = RequestMethod.GET)
    public String updateActiveOrder(@PathVariable(value = "name")String itemName,Model model){
        System.out.println("updateActiveOrder called");
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
        model.addAttribute("orderList",orderModel);
        //orderFinalized=true;
        return "DineIn/form::orderlist";
    }

    @RequestMapping(value="/saveOrder", method = RequestMethod.POST)
    public String saveOrder(){
        System.out.println("saveOrder called");
        Float grandTotal=new Float(0);

            for(Orderentity o:new ArrayList<>(activeOrderList.getActiveOrderList().values())){
                System.out.println(o.getItemname()+"\t"+o.getNumber()+"\t"+o.getPrice()+"\t"+o.getTotal());
                grandTotal=grandTotal+o.getTotal();
            }
            System.out.println("Total: "+grandTotal);

        return "redirect:dinein";
    }
   /* public String saveOrder(@ModelAttribute("orderList")OrderModel orderModel){
        System.out.println("saveOrder called");
        if(orderFinalized){
            for(Orderentity o:orderModel.getOrderentities()){
                System.out.println(o.getItemname()+"\t"+o.getNumber()+"\t"+o.getPrice()+"\t"+o.getTotal());
            }
            System.out.println("Total: "+orderModel.getGrandTotal());
        }
        else {
            System.out.println("Order not finalized, Press calculate again!!");
            //TODO: Press Calculate Notification Display
        }
        return "redirect:dinein";
    }*/

}
