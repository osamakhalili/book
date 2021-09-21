package com.example.book;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String  name;
    private String  author;
    private String  image;

    public Book() {
    }

    public Book(Long id, String name, String author, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.image = image;
    }

    public Book(String name, String author, String image) {
        this.name = name;
        this.author = author;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
