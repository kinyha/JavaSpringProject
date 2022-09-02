package ru.bratchikov.project1.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 1900, message = "Year should be greater than 1900")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> books;
    public Person(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean hasBook() {
        return true;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
