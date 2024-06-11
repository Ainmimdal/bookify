package com.mimdal.bookify.views;
import com.mimdal.bookify.controllers.BookListController;
import com.mimdal.bookify.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.List;



public class BookListView {
    private VBox view;
    private ObservableList<Book> books;
    private BookListController controller;

    public BookListView() {
        books = FXCollections.observableArrayList();
        createView();
    }

    public void setController(BookListController controller) {
        this.controller = controller;
    }

    private void createView() {
        view = new VBox(10);
        view.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(20));
        view.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("Book Catalog");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search books...");
        searchField.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-border-radius: 5px;");

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("All", "Programming", "Fantasy");
        categoryComboBox.setValue("All");
        categoryComboBox.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-border-radius: 5px;");

        ComboBox<String> authorComboBox = new ComboBox<>();
        authorComboBox.getItems().addAll("All", "Joshua Bloch", "Robert C. Martin", "J.R.R. Tolkien");
        authorComboBox.setValue("All");
        authorComboBox.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-border-radius: 5px;");

        HBox filterBox = new HBox(10, searchField, categoryComboBox, authorComboBox);
        filterBox.setAlignment(Pos.CENTER);

        ListView<Book> bookListView = new ListView<>(books);
        bookListView.setCellFactory(param -> new BookCell());

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        Button viewCartButton = new Button("View Cart");
        viewCartButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        Button viewOrdersButton = new Button("View Orders");
        viewOrdersButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        HBox buttonsBox = new HBox(10, addToCartButton, viewCartButton, viewOrdersButton);
        buttonsBox.setAlignment(Pos.CENTER);

        view.getChildren().addAll(titleLabel, filterBox, bookListView, buttonsBox);

        // Button actions
        searchField.setOnAction(e -> controller.searchBooks(searchField.getText()));
        categoryComboBox.setOnAction(e -> controller.loadBooksByCategory(categoryComboBox.getValue()));
        authorComboBox.setOnAction(e -> controller.loadBooksByAuthor(authorComboBox.getValue()));
        addToCartButton.setOnAction(e -> {
            Book selectedBook = bookListView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                controller.addToCart(selectedBook);
            }
        });
        viewCartButton.setOnAction(e -> controller.viewCart());
        viewOrdersButton.setOnAction(e -> controller.viewOrders());
    }

    public VBox getView() {
        return view;
    }

    public void setBooks(List<Book> books) {
        this.books.setAll(FXCollections.observableArrayList(books));
    }

    private static class BookCell extends javafx.scene.control.ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);
            if (empty || book == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(null);
                VBox cell = new VBox(10);
                cell.setAlignment(Pos.CENTER_LEFT);

                HBox bookInfo = new HBox(10);
                bookInfo.setAlignment(Pos.CENTER_LEFT);

                ImageView bookImage = new ImageView();
                bookImage.setImage(new Image("file:" + book.getImagePath()));
                bookImage.setFitHeight(100);
                bookImage.setFitWidth(70);

                VBox bookDetails = new VBox(5);
                bookDetails.setAlignment(Pos.CENTER_LEFT);

                Label titleLabel = new Label(book.getTitle());
                titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");
                Label authorLabel = new Label("by " + book.getAuthor());
                authorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");
                Label isbnLabel = new Label("ISBN: " + book.getIsbn());
                isbnLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #777777;");
                Label priceLabel = new Label("$" + book.getPrice());
                priceLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                bookDetails.getChildren().addAll(titleLabel, authorLabel, isbnLabel, priceLabel);
                bookInfo.getChildren().addAll(bookImage, bookDetails);

                cell.getChildren().add(bookInfo);
                setGraphic(cell);
            }
        }
    }
}