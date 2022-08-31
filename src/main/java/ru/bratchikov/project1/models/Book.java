package ru.bratchikov.project1.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;
    private int userId;
    @NotEmpty(message = "Title is empty")
    @Size(min = 1, max = 30, message = "Title must be between 1 and 30 characters")
    private String title;
    @NotEmpty(message = "Author is empty")
    @Size(min = 1, max = 30, message = "Author must be between 1 and 30 characters")
    private String author;
    @Min(value = 0, message = "Year should be greater than 0")
    private int year;


    public Book() {
    }

    public Book(int bookId,int userId, String title, String author, int year) {
        this.bookId = bookId;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isUnAvailable() {
        return this.userId != 0;
    }
}
