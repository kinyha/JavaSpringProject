//package ru.bratchikov.project1.dao;

//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import ru.bratchikov.project1.models.Book;
//import ru.bratchikov.project1.models.Person;
//
//import java.util.List;

//@Component
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int userId) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE user_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
////    public Person showByBook(int bookId) {
////        return jdbcTemplate.query("SELECT * FROM person WHERE book_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Person.class))
////                .stream().findAny().orElse(null);
////    }
//
//    public void save(Person person, Book book) {
//        jdbcTemplate.update("INSERT INTO person (name, age) VALUES (?, ?)", person.getName(), person.getAge());
//    }
//
//    public void update(int userID, Person updatedPerson) { //+
//        jdbcTemplate.update("UPDATE person SET name = ?, age = ? WHERE user_id = ?", updatedPerson.getName(), updatedPerson.getAge(), userID);
//    }
//
//    public void delete(int userID) {
//        jdbcTemplate.update("DELETE FROM person WHERE user_id = ?", userID);
//    }
//
//    public List<Book> showBooks(int userId) {
////        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Book.class))
////                .stream().findAny().orElse(null);
//        return jdbcTemplate.query("SELECT * FROM book WHERE user_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Book.class));
//    }

//    public List<Book> showBooks(int userId) {
////        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Book.class))
////                .stream().findAny().orElse(null);
//        return jdbcTemplate.query("SELECT * FROM book WHERE user_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Book.class));
//    }


//}
