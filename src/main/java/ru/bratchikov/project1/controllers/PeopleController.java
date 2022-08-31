package ru.bratchikov.project1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bratchikov.project1.dao.PersonDAO;
import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;


    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int userId, Model model) {
        model.addAttribute("person", personDAO.show(userId));
        model.addAttribute("books", personDAO.showBooks(userId));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,@ModelAttribute("book") Book book,
                         BindingResult bindingResult) {
        personDAO.save(person, book);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int userId) {
        model.addAttribute("person", personDAO.show(userId));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")@Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int userId) {
        personDAO.update(userId, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int userId) {
        personDAO.delete(userId);
        return "redirect:/people";
    }


}
