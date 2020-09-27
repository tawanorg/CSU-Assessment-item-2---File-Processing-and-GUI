package com.tawan;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * DeleteBook
 *
 * @author Tawan Chotikanchinda <hello@tawan.org>
 */
public class BookCollection {
    /**
     * @var
     */
    private ArrayList<Book> books;

    public BookCollection(ArrayList<Book> books) {
        this.books = books;
    }

    public Book findByTitle(String bookTitle) {
        Book myBook = null;

        if (bookTitle.isEmpty()) {
            return null;
        }

        try {
            myBook = this.books.stream()
                    .filter(book -> book.title.contains(bookTitle))
                    .findAny()
                    .get();

        } catch ( NoSuchElementException e) {
            System.out.println(bookTitle + " not found!");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return myBook;
    }

    public Book findByAuthor(String bookAuthor) {
        Book myBook = null;
        try {
            myBook = this.books.stream()
                    .filter(book -> book.author.contains(bookAuthor))
                    .findAny()
                    .get();

        } catch ( NoSuchElementException e) {
            System.out.println("No book written by " + bookAuthor);
        }

        return myBook;
    }

    public Book findByYearOfPublication(int year) {
        Book myBook = null;
        try {
            myBook = this.books.stream()
                    .filter(book -> book.year == year)
                    .findAny()
                    .get();

        } catch ( NoSuchElementException e ) {
            System.out.println("No book in entered year of " + year);
        }

        return myBook;
    }

    public Book findByISBN(int ISBN) {
        Book myBook = null;

        try {
            myBook = this.books.stream()
                    .filter(book -> book.ISBN == ISBN)
                    .findAny()
                    .get();

        } catch ( NoSuchElementException e ) {
            System.out.println("No book with ISBN: " + ISBN);
        }

        return myBook;
    }
}
