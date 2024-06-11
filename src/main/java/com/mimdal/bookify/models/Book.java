package com.mimdal.bookify.models;

public class Book {
    private String title;
    private String author;
    private String category;
    private double price;
    private String isbn;
    private String imagePath;

    public Book(String title, String author, String category, double price, String isbn, String imagePath) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.isbn = isbn;
        this.imagePath = imagePath;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getImagePath() {
        return imagePath;
    }
}