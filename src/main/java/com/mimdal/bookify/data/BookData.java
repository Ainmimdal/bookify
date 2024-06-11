package com.mimdal.bookify.data;


import com.mimdal.bookify.models.Book;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


public class BookData {
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("Effective Java", "Joshua Bloch", "Programming",  45.00, "978-0134685991","/images/effective_java.jpg"));
        books.add(new Book("Clean Code", "Robert C. Martin", "Programming", 40.00, "978-0132350884", "/images/clean_code.jpg"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy",  25.00,"978-0547928227", "/images/the_hobbit.jpg"));
        books.add(new Book("A Complete Study Guide to Biology", "Gabrielle I. Edwards", "Biology", 56.00, "978-1506281322","/images/biology.jpg"));
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Book> getBooksByCategory(String category) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public static List<Book> getBooksByAuthor(String author) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public static List<Book> searchBooks(String keyword) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getIsbn().contains(keyword)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
}