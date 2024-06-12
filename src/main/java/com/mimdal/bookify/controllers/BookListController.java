package com.mimdal.bookify.controllers;

import com.mimdal.bookify.Utility.UserUtility;
import com.mimdal.bookify.data.BookData;
import com.mimdal.bookify.data.OrderData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.CartView;
import com.mimdal.bookify.views.OrderListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookListController {
    private BookListView bookListView;
    private User user;
    private CartController cartController;
    private Stage primaryStage;

    public BookListController(User user, Stage primaryStage) {
        this.user = user;
        this.primaryStage = primaryStage;
        this.cartController = new CartController();
        CartView cartView = new CartView(cartController);
        cartController.setView(cartView);
    }

    public void setBookListView(BookListView bookListView) {
        this.bookListView = bookListView;
        this.bookListView.setBooks(BookData.getBooks());
    }

    public void loadBooks() {
        bookListView.setBooks(BookData.getBooks());
    }

    public void loadBooksByCategory(String category) {
        if ("All".equals(category)) {
            bookListView.setBooks(BookData.getBooks());
        } else {
            bookListView.setBooks(BookData.getBooksByCategory(category));
        }
    }

    public List<String> loadCategories() {
        Set<String> categories = new HashSet<>();
        categories.add("All");
        for (Book book : BookData.getBooks()) {
            categories.add(book.getCategory());
        }
        return new ArrayList<>(categories);
    }

    public List<String> loadAuthors() {
        Set<String> authors = new HashSet<>();
        authors.add("All");
        for (Book book : BookData.getBooks()) {
            authors.add(book.getAuthor());
        }
        return new ArrayList<>(authors);
    }
    public void loadBooksByAuthor(String author) {
        if ("All".equals(author)) {
            bookListView.setBooks(BookData.getBooks());
        } else {
            bookListView.setBooks(BookData.getBooksByAuthor(author));
        }
    }

    public void searchBooks(String keyword) {
        bookListView.setBooks(BookData.searchBooks(keyword));
    }

    public void addToCart(Book book) {
        cartController.addToCart(book);
        System.out.println(book.getTitle() + " added to cart.");
    }

    public void viewCart() {
        cartController.getView().show();
    }

    public void viewOrders() {
        OrderListView orderListView = new OrderListView(OrderData.getOrdersByUser(user), this);
        orderListView.show();
    }

    public void placeOrder() {
        cartController.checkout();
    }

    public void logout() {
        UserUtility.currentUser = null; // Clear current user
        showLoginView();
    }

    private void showLoginView() {
        primaryStage.close();
        Stage loginStage = new Stage();
        LoginController loginController = new LoginController(loginStage); // Create LoginController with the new stage
        loginController.showLoginView();
    }
}
