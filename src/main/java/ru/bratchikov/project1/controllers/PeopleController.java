package ru.bratchikov.project1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Person;
import ru.bratchikov.project1.repositories.BooksRepository;
import ru.bratchikov.project1.services.PeopleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    //private final PersonDAO personDAO;
    private final PeopleService peopleService;
    private final BooksRepository booksRepository;



    public PeopleController(PeopleService peopleService, BooksRepository booksRepository) {
        this.peopleService = peopleService;
        this.booksRepository = booksRepository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int userId, Model model) {
        model.addAttribute("person", peopleService.findOne(userId));
        model.addAttribute("books", booksRepository.findByPersonUserId(userId));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int userId) {
        model.addAttribute("person", peopleService.findOne(userId));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int userId) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(userId, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int userId) {
        peopleService.delete(userId);
        return "redirect:/people";
    }
    public List<Book> showBooks(int userId) {
        return booksRepository.findByPersonUserId(userId);
    }

}
