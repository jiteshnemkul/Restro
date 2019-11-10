package com.jit.resto.controller;

import com.jit.resto.model.CategoryEntity;
import com.jit.resto.model.Customers;
import com.jit.resto.repository.CategoryRepository;
import com.jit.resto.service.CategoryService;
import com.jit.resto.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private CategoryService categoryService;



    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category")
    public String index() {
        return "redirect:/category/1";
    }

    @RequestMapping(value = "/category/{pageNumber}", method = RequestMethod.GET)
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<CategoryEntity> page = categoryService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "Category/list";

    }

    @RequestMapping("/category/add")
    public String add(Model model) {

        model.addAttribute("category", new CategoryEntity());
        return "Category/form";

    }

    @RequestMapping("/category/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("category", categoryService.get(id));
        return "Category/form";

    }

    @RequestMapping(value = "/category/save", method = RequestMethod.POST)
    public String save(CategoryEntity customer, final RedirectAttributes ra) {

        CategoryEntity save = categoryService.save(customer);
        ra.addFlashAttribute("successFlash", "Cliente foi salvo com sucesso.");
        return "redirect:/category";

    }

    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable Long id) {

        categoryService.delete(id);
        return "redirect:/category";

    }
}
