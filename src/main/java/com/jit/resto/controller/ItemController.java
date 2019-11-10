package com.jit.resto.controller;

import com.jit.resto.model.CategoryEntity;
import com.jit.resto.model.ItemEntity;
import com.jit.resto.model.ItemModel;
import com.jit.resto.service.CategoryService;
import com.jit.resto.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/item")
    public String index() {
        return "redirect:/item/1";
    }

    @RequestMapping(value = "/item/{pageNumber}", method = RequestMethod.GET)
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<ItemEntity> page = itemService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "Item/list";

    }

    @RequestMapping("/item/add")
    public String add(Model model) {

        model.addAttribute("item", new ItemEntity());
        return "Item/form";

    }

    @RequestMapping("/item/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("item", itemService.get(id));
        return "Item/form";

    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    public String save(ItemEntity itemEntity, final RedirectAttributes ra) {
        /*ItemModel list=new ItemModel();
         Long categoryid=categoryService.getcategoryid(itemEntity.getCategoryname());
         list.getItemEntity().setCategoryid(categoryid);
         list.getItemEntity().setItemname(itemEntity.getItemEntity().getItemname());
         list.getItemEntity().setDescription(itemEntity.getItemEntity().getDescription());
         list.getItemEntity().setPrice(itemEntity.getItemEntity().getPrice());
         Date date=new Date();

         list.getItemEntity().setCreatedDate(date.toString());*/
        ItemEntity save = itemService.save(itemEntity);
        ra.addFlashAttribute("successFlash", "Saved succefully");
        return "redirect:/item";

    }

    @RequestMapping("/item/delete/{id}")
    public String delete(@PathVariable Long id) {

        itemService.delete(id);
        return "redirect:/item";

    }

    @ModelAttribute("logs")
    public  List<CategoryEntity> log(Model model) {
        List<CategoryEntity> logs = categoryService.findAll();

        return logs;
    }


    @ModelAttribute("createdate")
    public String date(Model model){
        Date today=new Date();
      String date=today.toString();
        return date;
    }

}
