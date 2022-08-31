package ru.bratchikov.project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int userId;
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @Min(value = 1900, message = "Year should be greater than 1900")
    private int age;

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
}
