package org.nikik0.controllers;

import org.nikik0.dao.BookDao;
import org.nikik0.dao.CustomerDao;
import org.nikik0.models.Book;
import org.nikik0.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDao bookDao;
    private final CustomerDao customerDao;

    @Autowired
    public BooksController(BookDao bookDao, CustomerDao customerDao) {
        this.bookDao = bookDao;
        this.customerDao = customerDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("customer") Customer customer){
        model.addAttribute("books", bookDao.show(id));
        model.addAttribute("customerTaken", bookDao.takenBy(id));
        model.addAttribute("customers", customerDao.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "books/new";
        bookDao.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @ModelAttribute("customer") Customer customer,  @PathVariable("id") int id){
        System.out.println("update called");
        if (bindingResult.hasErrors()) return "books/new";
        bookDao.update(id,book);
        //bookDao.setTaken(customer.getCustomerid(), id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("books", bookDao.show(id));
        return "books/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setowner")
    public String setOwner(@ModelAttribute("custom") @Valid Customer customer, BindingResult bindingResult, @PathVariable("id") int id){
        bookDao.setTaken(customer.getCustomerid(), id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        System.out.println("release called");
        bookDao.releaseTaken(id);
        return "redirect:/books/"+id;
    }
}
