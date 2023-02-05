package org.nikik0.controllers;

import org.nikik0.dao.BookDao;
import org.nikik0.dao.CustomerDao;
import org.nikik0.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomersController {
    private final CustomerDao customerDao;
    private final BookDao bookDao;
    @Autowired
    public CustomersController(CustomerDao customerDao, BookDao bookDao) {
        this.customerDao = customerDao;
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("customers",customerDao.index());
        return "customers/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("customers", customerDao.show(id));
        return "customers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customers") @Valid Customer customer, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()) return "customers/new";
        customerDao.update(id, customer);
        return "redirect:/customers";
    }

    @GetMapping("/new")
    public String newCustomer(@ModelAttribute("customers") Customer customer){
        return "customers/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("customers") @Valid Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "customers/new";
        customerDao.save(customer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        customerDao.delete(id);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("customers", customerDao.show(id));
        model.addAttribute("books", bookDao.indexTaken(id));
        return "customers/show";
    }



}
