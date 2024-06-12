package com.mimdal.bookify.models;

import java.util.List;
import java.util.UUID;

public class Order {
    private static int nextId = 1;
    private int id;
    private User user;
    private List<Book> books;
    private double totalAmount;

    public Order(User user, List<Book> books) {
        this.id = nextId++;
        this.user = user;
        this.books = books;
        this.totalAmount = calculateTotal(books);
    }

    private double calculateTotal(List<Book> books) {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }

    public int getId() {
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
}