package ru.bratchikov.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Person;
import ru.bratchikov.project1.services.BooksService;
import ru.bratchikov.project1.services.PeopleService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    public String index(Model model, HttpServletRequest request) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int books_per_page = request.getParameter("books_per_page") == null ? 1 : Integer.parseInt(request.getParameter("books_per_page"));
        Boolean isSorted = Boolean.valueOf(request.getParameter("sort_by_year"));
        List<Book> books = booksService.getAllSortedByYearAndPage(page, books_per_page);
        if (isSorted) {
            model.addAttribute("books", books);
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int bookId, Model model) {
        model.addAttribute("book", booksService.findOne(bookId));
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("personByBookId", booksService.findPersonByBookId(bookId));
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
    public String giveBook(@ModelAttribute("person") Person person,
                           BindingResult bindingResult, @PathVariable("id") int bookId) {
        System.out.println(person.getUserId() + " -- " + bookId);
        booksService.giveBook(person.getUserId(), bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        booksService.freeBook(bookId);
        return "redirect:/books";
    }

    @PostMapping ("/search")
    public String search(@ModelAttribute("query") String query, Model model) {
        model.addAttribute("book", booksService.findBookByTitle(query));
        return "books/search";
    }
//    @PatchMapping("/search/{searchQuery}")
//    public String search(@ModelAttribute("searchQuery") String searchQuery, Model model) {
//        //model.addAttribute("books", booksService.search(searchQuery));
//        return "books/index";
//    }

    @GetMapping("/search")
    public String search(Model model) {
        //model.addAttribute("book", booksService.findBookByTitle(search));
        return "books/search";
    }


//        model.addAttribute("book", new Book());
//        return "books/search";
//    }

//    @PostMapping("/search")
//    public String search(@ModelAttribute("book") @Valid Book book,
//                         BindingResult bindingResult) {
//
//        booksService.findBookByTitle(book.getTitle());
//        return "redirect:/books/search";
//    }

}
