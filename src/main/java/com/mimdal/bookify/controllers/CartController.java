package com.mimdal.bookify.controllers;

import com.mimdal.bookify.Utility.UserUtility;
import com.mimdal.bookify.data.OrderData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.Order;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.CartView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
public class CartController {
    private CartView view;
    private List<Book> cartItems;

    public CartController() {
        this.cartItems = new ArrayList<>();
    }

    public void setView(CartView view) {
        this.view = view;
        this.view.setCartItems(cartItems);
    }

    public void addToCart(Book book) {
        cartItems.add(book);
        if (view != null) {
            view.setCartItems(cartItems);
        }
    }

    public void checkout() {
        // Create a copy of the cart items
        List<Book> orderedBooks = new ArrayList<>(cartItems);

        // Clear the cart
        cartItems.clear();
        if (view != null) {
            view.setCartItems(cartItems);
        }

        // Create and add the order
        Order order = new Order(UserUtility.currentUser, orderedBooks);
        OrderData.addOrder(order);

        // Show confirmation
        if (view != null) {
            view.showConfirmation("Your order has been placed successfully!");
        }
    }

    public CartView getView() {
        return view;
    }
}
