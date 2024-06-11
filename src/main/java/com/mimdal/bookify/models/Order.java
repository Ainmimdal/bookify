package com.mimdal.bookify.models;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private User user;
    private List<Book> books;
    private double totalAmount;

    public Order(User user, List<Book> books) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.books = books;
        this.totalAmount = calculateTotalAmount();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    private double calculateTotalAmount() {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }
}