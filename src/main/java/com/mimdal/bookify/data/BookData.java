package com.mimdal.bookify.data;


import com.mimdal.bookify.models.Book;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class BookData {
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("Effective Java", "Joshua Bloch", "Programming", 45.00, "9780134685991", "src/images/effective_java.jpg"));
        books.add(new Book("Clean Code", "Robert C. Martin", "Programming", 40.00, "9780132350884", "src/images/clean_code.jpg"));
        books.add(new Book("The Pragmatic Programmer", "Andrew Hunt", "Programming", 50.00, "9780201616224", "src/images/pragmatic_programmer.jpg"));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 30.00, "9780747532699", "src/images/harry_potter.jpg"));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 35.00, "9780261102385", "src/images/lord_of_the_rings.jpg"));
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Book> getBooksByCategory(String category) {
        List<Book> categoryBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                categoryBooks.add(book);
            }
        }
        return categoryBooks;
    }

    public static List<Book> getBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public static void addBook(Book book) {
        books.add(book);
    }
}