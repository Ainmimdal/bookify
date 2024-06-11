package com.mimdal.bookify.controllers;

import com.mimdal.bookify.data.BookData;
import com.mimdal.bookify.data.OrderData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.CartView;
import com.mimdal.bookify.views.OrderListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class BookListController {
    private BookListView view;
    private ObservableList<Book> books;

    public BookListController() {
        this.books = FXCollections.observableArrayList();
    }

    public void setView(BookListView view) {
        this.view = view;
    }

    public void loadBooks() {
        books.clear();
        books.addAll(getAllBooks());
        view.setBooks(books);
    }

    public void loadBooksByCategory(String category) {
        List<Book> filteredBooks = getAllBooks().stream()
                .filter(book -> book.getCategory().equals(category))
                .collect(Collectors.toList());
        view.setBooks(filteredBooks);
    }

    public void loadBooksByAuthor(String author) {
        List<Book> filteredBooks = getAllBooks().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
        view.setBooks(filteredBooks);
    }

    public void searchBooks(String query) {
        List<Book> filteredBooks = getAllBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        view.setBooks(filteredBooks);
    }

    public void addToCart(Book book) {
        // Add the selected book to the user's cart
    }

    public void viewCart() {
        // Navigate to the cart view
    }

    public void viewOrders() {
        // Navigate to the orders view
    }

    private List<Book> getAllBooks() {
        // Return all books in the catalog
        return List.of(
                new Book("Effective Java", "Joshua Bloch", "Programming", 45.00, "9780134685991", "src/images/effective_java.jpg"),
                new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 39.99, "9780747532699", "src/images/harry_potter.jpg"),
                new Book("Clean Code", "Robert C. Martin", "Programming", 50.00, "9780132350884", "src/images/clean_code.jpg"),
                new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 25.00, "9780547928227", "src/images/the_hobbit.jpg"),
                new Book("Design Patterns", "Erich Gamma", "Programming", 60.00, "9780201633610", "src/images/design_patterns.jpg")
        );
    }
}