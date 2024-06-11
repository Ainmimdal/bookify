package com.mimdal.bookify.controllers;

import com.mimdal.bookify.data.BookData;
import com.mimdal.bookify.data.OrderData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.CartView;
import com.mimdal.bookify.views.OrderListView;



public class BookListController {
    private BookListView bookListView;
    private User user;
    private CartController cartController;

    public BookListController(User user) {
        this.user = user;
        CartView cartView = new CartView(new CartController());
        this.cartController = new CartController();
        this.cartController.setView(cartView);
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
}
