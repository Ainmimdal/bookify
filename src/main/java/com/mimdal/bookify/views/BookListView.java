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
        this.books = FXCollections.observableArrayList();
        createView();
    }

    private void createView() {
        view = new VBox();
        view.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(50));
        view.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("Book Catalog");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        TextField searchField = new TextField();
        searchField.setPromptText("Search by title or author");
        searchField.setPrefWidth(300);
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        searchButton.setOnAction(e -> controller.searchBooks(searchField.getText()));
        searchBox.getChildren().addAll(searchField, searchButton);

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("All", "Programming", "Fantasy");
        categoryComboBox.setValue("All");
        categoryComboBox.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-border-radius: 5px;");
        categoryComboBox.setOnAction(e -> {
            String selectedCategory = categoryComboBox.getValue();
            if ("All".equals(selectedCategory)) {
                controller.loadBooks();
            } else {
                controller.loadBooksByCategory(selectedCategory);
            }
        });

        ComboBox<String> authorComboBox = new ComboBox<>();
        authorComboBox.getItems().addAll("All Authors"); // Add authors dynamically
        authorComboBox.setValue("All Authors");
        authorComboBox.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-border-radius: 5px;");
        authorComboBox.setOnAction(e -> {
            String selectedAuthor = authorComboBox.getValue();
            if ("All Authors".equals(selectedAuthor)) {
                controller.loadBooks();
            } else {
                controller.loadBooksByAuthor(selectedAuthor);
            }
        });

        HBox filterBox = new HBox(10, categoryComboBox, authorComboBox);
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

        view.getChildren().addAll(titleLabel, searchBox, filterBox, bookListView, buttonsBox);

        // Button actions
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

    public void setController(BookListController controller) {
        this.controller = controller;
    }

    public void setBooks(List<Book> books) {
        ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
        this.books.setAll(observableBooks);
    }

    private static class BookCell extends javafx.scene.control.ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);
            if (empty || book == null) {
                setText(null);
                setGraphic(null);
            } else {
                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.setPadding(new Insets(10));

                Label titleLabel = new Label(book.getTitle());
                titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333333;");
                Label authorLabel = new Label("by " + book.getAuthor());
                Label priceLabel = new Label("$" + book.getPrice());
                Label isbnLabel = new Label("ISBN: " + book.getIsbn());

                File file = new File(book.getImagePath());
                ImageView imageView = new ImageView(new Image(file.toURI().toString()));
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                gridPane.add(imageView, 0, 0, 1, 3);
                gridPane.add(titleLabel, 1, 0);
                gridPane.add(authorLabel, 1, 1);
                gridPane.add(priceLabel, 1, 2);
                gridPane.add(isbnLabel, 1, 3);

                setGraphic(gridPane);
            }
        }
    }
}