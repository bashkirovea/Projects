package ru.bash.spring.BootLibraryApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Table;


import java.util.Date;

@Entity
@Table(appliesTo = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "title")
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 3, max = 100, message = "Название должно состоять от трех до ста символов")
    private String title;

    @Column(name = "year")
    @Min(value = 1700, message = "Самый ранний год написания книги - 1700 г. ")
    private int year;

    @Column(name = "author")
    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Size(min = 3, max = 100, message = "ФИО должно состоять от трех до ста символов")
    private String author;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;


    public Book() {

    }

    public Book(String title, int year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
