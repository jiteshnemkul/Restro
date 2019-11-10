package com.jit.resto.controller;

import com.jit.resto.model.CategoryEntity;
import com.jit.resto.model.TableEntity;
import com.jit.resto.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TableController {

    private TableService tableService;



    @Autowired
    public void setTableService(TableService tableService) {
        this.tableService = tableService;
    }

    @RequestMapping(value = "/table")
    public String index() {
        return "redirect:/table/1";
    }

    @RequestMapping(value = "/table/{pageNumber}", method = RequestMethod.GET)
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<TableEntity> page = tableService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "Table/list";

    }

    @RequestMapping("/table/add")
    public String add(Model model) {

        model.addAttribute("table", new TableEntity());
        return "Table/form";

    }

    @RequestMapping("/table/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("table", tableService.get(id));
        return "Table/form";

    }

    @RequestMapping(value = "/table/save", method = RequestMethod.POST)
    public String save(TableEntity tableEntity, final RedirectAttributes ra) {

        TableEntity save = tableService.save(tableEntity);
        ra.addFlashAttribute("successFlash", "Cliente foi salvo com sucesso.");
        return "redirect:/table";

    }

    @RequestMapping("/table/delete/{id}")
    public String delete(@PathVariable Long id) {

        tableService.delete(id);
        return "redirect:/table";

    }

}
