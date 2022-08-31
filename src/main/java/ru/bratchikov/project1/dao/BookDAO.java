package ru.bratchikov.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bratchikov.project1.models.Book;
import ru.bratchikov.project1.models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT book_id,title,author,year FROM book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int bookId) {
        return jdbcTemplate.query("SELECT book_id,title,author,year FROM book WHERE book_id = ?",
                        new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public Person showPersonByBookId(int bookId) {
        //return jdbcTemplate.query("SELECT user_id FROM book WHERE book_id = ?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
              //  .stream().findAny().orElse(null);
        //можно было тут сделать Join
        return jdbcTemplate.query("SELECT * FROM Person WHERE user_id = (SELECT user_id FROM Book where book_id = ?)", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author, year) VALUES (?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year = ? WHERE book_id = ?"
                , updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), bookId);
    }

    public void delete(int bookID) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", bookID);
    }

//    public void isAvailable(int bookId) {
//        jdbcTemplate.update("UPDATE book SET is_available = ? WHERE book_id = ?", false, bookId);
//
//    }
    public void giveBook(int userId, int bookId) {
        jdbcTemplate.update("UPDATE book SET user_id = ? WHERE book_id = ?", userId, bookId);
    }


    public void  freeBook(int bookId) {
        jdbcTemplate.update("UPDATE book SET user_id = NULL WHERE book_id = ?", bookId);
    }
}
