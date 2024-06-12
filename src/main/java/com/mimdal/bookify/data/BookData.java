package com.mimdal.bookify.data;


import com.mimdal.bookify.models.Book;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


public class BookData {
    private static List<Book> books = new ArrayList<>();

    static {
//        books.add(new Book("Effective Java", "Joshua Bloch", "Programming",  45.00, "978-0134685991","/images/effective_java.jpg"));
//        books.add(new Book("Clean Code", "Robert C. Martin", "Programming", 40.00, "978-0132350884", "/images/clean_code.jpg"));
//        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy",  25.00,"978-0547928227", "/images/the_hobbit.jpg"));
        books.add(new Book("A Complete Study Guide to Biology", "Gabrielle I. Edwards", "Biology", 56.00, "978-1506281322","/images/biology.jpg"));
        books.add(new Book("Everything You Need to Ace Math", "Workman Publishing", "Mathematic", 32.00, "978-0761160960","/images/math.jpg"));
        books.add(new Book("American History", "Garth Sundem", "History", 59.00, "978-1425803704","/images/american history.jpg"));
        books.add(new Book("Learn Korean", "Jennie Lee", "Language", 38.00, "978-1838291648","/images/korean.jpg"));
        books.add(new Book("JavaX A Beginners Guide", "J.F. DiMarzio", "Programming", 188.00, "978-0071742412","/images/java.jpg"));
        books.add(new Book("The C Programming Language ", "   Brian W. Kernighan, Dennis M. Ritchie", "Programming", 284.60, "978-0131103627","/images/The C Programming Language  (Brian W. Kernighan ,  Dennis M. Ritchie).jpeg"));
        books.add(new Book("Information Technology Project Management: Providing Measurable Organizational Value ", "  Jack T. Marchewka", "Information Technology  ", 646.31, "978-1118911013","/images/(Matthew Smith) IT Infrastructure for the Complete Beginner.jpeg"));
        books.add(new Book("Algorithms to Live By: The Computer Science of Human Decisions ", "  Brian Christian, Tom Griffiths", "Computer Science ", 86.88, "978-1627790369","/images/Algorithms to Live By_ The Computer Science of Human Decisions  (Brian Christian and Tom Griffiths).jpeg"));
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

    public static List<String> listAuthor(){
        List<String> authors = new ArrayList<>();

        for(Book book:books){
            if(book.getAuthor()!= null) {
                authors.add(book.getAuthor());
            }
        }
        return authors;
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