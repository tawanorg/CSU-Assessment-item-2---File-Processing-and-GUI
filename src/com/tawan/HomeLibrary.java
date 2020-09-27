package com.tawan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * HomeLibrary
 *
 * @author Tawan Chotikanchinda <hello@tawan.org>
 */
public class HomeLibrary {
    /**
     * @var
     */
    private boolean startProgram = true;

    /**
     * @var
     */
    private final BookBinary bookBinary;

    /**
     * HomeLibrary instance
     * @param inputFile binary data path file
     */
    public HomeLibrary(String inputFile) {
        this.bookBinary = new BookBinary(inputFile);
    }

    /**
     * Start application
     * @throws Exception
     */
    public void start() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (this.startProgram) {

            System.out.println("" +
                    "1. Add a new book\n" +
                    "2. Delete a book\n" +
                    "3. Search for a book\n" +
                    "4. Display all books\n" +
                    "5. Exit\n");

            System.out.println("Please enter menu above:");
            int menuOption = scanner.nextInt();

            switch (menuOption) {
                case 1 -> this.addBook();
                case 2 -> this.deleteBook();
                case 3 -> this.searchBook();
                case 4 -> this.displayBooks();
                case 5 -> this.startProgram = false;
            }
        }
    }

    /**
     * Add book
     */
    private void addBook() {
        try {
            Scanner myBook = new Scanner(System.in);

            System.out.println("Title of the book");
            String titleOfTheBook = myBook.nextLine();

            System.out.println("Author of the book");
            String authorOfTheBook = myBook.nextLine();

            System.out.println("Year of publication");
            int yearOfPublication = myBook.nextInt();

            System.out.println("ISBN number");
            int isbnNumber = myBook.nextInt();

            // create book object
            Book book = new Book(titleOfTheBook, authorOfTheBook, yearOfPublication, isbnNumber);
            // write book into binary file
            this.bookBinary.setBook(book);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a book
     */
    private void deleteBook() {
        System.out.println("deleteBook");
    }

    /**
     * Search a book
     */
    private void searchBook() {
        System.out.println("searchBook");
    }

    /**
     * Display all books
     *
     * @throws Exception
     */
    private void displayBooks() throws Exception {
        // get books list from binary file
        ArrayList<Book> books = this.bookBinary.getBooks();

        System.out.println("## We have " + books.size() + " books in library ##");

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("##  ------  ##");

    }
}
