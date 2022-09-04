package ru.bratchikov.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Person;
import ru.bratchikov.project1.services.BooksService;
import ru.bratchikov.project1.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    //
//    private final BookDAO bookDAO;
//    private final PersonDAO personDAO;
    private final BooksService booksService;
    private final PeopleService peopleService;

    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear) {
        if (sortByYear == null) {
            sortByYear = false;
        }
        if (page == null || booksPerPage == null) {
            model.addAttribute("books", booksService.findAll(sortByYear));//
        } else {
            model.addAttribute("books", booksService.findWithPagination(page, booksPerPage, sortByYear));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int bookId, Model model) {
        model.addAttribute("book", booksService.findOne(bookId));
        Person bookOwner = booksService.getBookOwner(bookId);

        if (bookOwner != null) {
            model.addAttribute("personByBookId", bookOwner);
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }

    @PatchMapping("/add")
    public String add(@ModelAttribute("person") Person person, @ModelAttribute("book") Book book, BindingResult bindingResult) {
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
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int bookId) {
        model.addAttribute("book", booksService.findOne(bookId));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int bookId) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int bookId) {
        booksService.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/give")
    public String giveBook(@ModelAttribute("person") Person person, @PathVariable("id") int bookId) {
        booksService.giveBook(bookId, person);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        booksService.freeBook(bookId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping ("/search")  //?? @RequestParam("query")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("books", booksService.searchByTitle(query));
        return "books/search";
    }
    @GetMapping("/search")
    public String search() {
        return "books/search";
    }
}
