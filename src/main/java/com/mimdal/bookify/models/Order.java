package com.mimdal.bookify.models;

import java.util.List;

public class Order {
    private String username;
    private List<Book> books;
    private String status;

    public Order(String username, List<Book> books, String status) {
        this.username = username;
        this.books = books;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}