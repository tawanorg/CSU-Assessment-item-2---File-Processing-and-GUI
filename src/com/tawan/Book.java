package com.tawan;

/**
 * Book
 */
public class Book {
    public String title;
    public String author;
    public int year;
    public int ISBN;

    public Book(String title, String author, int yearOfPublication, int ISBN) {
        this.title = title;
        this.author = author;
        this.year = yearOfPublication;
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Year: " + year + "\n" +
                "ISBN: " + ISBN + "\n";
    }
}
