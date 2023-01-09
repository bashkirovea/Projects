package ru.bash.spring.BootLibraryApp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Table;

import java.util.List;

@Entity
@Table(appliesTo = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 3, max = 100, message = "ФИО должно состоять от трех до ста символов")
    private String fullName;

    @Column(name = "year_of_birth")
    @Max(value = 2010, message = "Минимальный возраст 12 лет")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    public Person() {

    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setId(Integer integer) {
        this.id = integer;
    }
}