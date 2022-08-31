package ru.bratchikov.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bratchikov.project1.dao.BookDAO;
import ru.bratchikov.project1.dao.PersonDAO;
import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person")Person person, @PathVariable("id") int bookId, Model model) {
        model.addAttribute("book", bookDAO.show(bookId));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("personByBookId", bookDAO.showPersonByBookId(bookId));
        return "books/show";
    }

    @PatchMapping("/add")
    public String add(@ModelAttribute("person")Person person, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int bookId) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")@Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int bookId) {
        bookDAO.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int bookId) {
        bookDAO.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/give")
    public String giveBook(@ModelAttribute("person")Person person,
                           BindingResult bindingResult, @PathVariable("id") int bookId) {
        //System.out.println(person.getUserId() + " -- " + bookId);
        bookDAO.giveBook(person.getUserId(), bookId);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        bookDAO.freeBook(bookId);
        return "redirect:/books";
    }

}
