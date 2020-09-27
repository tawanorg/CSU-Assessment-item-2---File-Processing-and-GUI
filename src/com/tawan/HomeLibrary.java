package com.tawan;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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
    private Book manageBook() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean startProgram = true;

        ArrayList<Book> books = this.bookBinary.getBooks();

        BookCollection bookCollection = new BookCollection(books);


        System.out.println("Search a book for deletion by\n");
        System.out.println("" +
                "1. Title\n" +
                "2. Author\n" +
                "3. Year of Publication\n" +
                "4. ISBN number");

        System.out.println("Please enter menu above:");
        int menuOption = scanner.nextInt();

        Book selectedBook = null;

        while (startProgram) {
            Scanner dataInput = new Scanner(System.in);
            selectedBook = null;

            switch (menuOption) {
                case 1 -> {
                    System.out.println("Please enter book title:");
                    String bookTitle = dataInput.nextLine();
                    selectedBook = bookCollection.findByTitle(bookTitle);
                }
                case 2 -> {
                    System.out.println("Please enter book author:");
                    String bookAuthor = dataInput.nextLine();
                    selectedBook = bookCollection.findByAuthor(bookAuthor);
                }
                case 3 -> {
                    System.out.println("Please enter year of publication:");
                    int bookYear = dataInput.nextInt();
                    selectedBook = bookCollection.findByYearOfPublication(bookYear);
                }
                case 4 -> {
                    System.out.println("Please enter ISBN:");
                    int ISBN = dataInput.nextInt();
                    selectedBook = bookCollection.findByISBN(ISBN);
                }
            }

            startProgram = false;
        }

        return selectedBook;
    }

    /**
     * Search a book
     */
    private void searchBook() throws Exception {
        Book book = this.manageBook();
        if (book != null) {
            System.out.println("Search result found!");
            System.out.println(book);
        }
    }

    /**
     * Search a book
     */
    private void deleteBook() throws Exception {
        Book book = this.manageBook();
        if (book != null) {
            System.out.println("The book " + book.title + " has been deleted!");
        }
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
