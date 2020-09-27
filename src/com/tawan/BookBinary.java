package com.tawan;

import java.io.*;
import java.util.ArrayList;

public class BookBinary {
    /**
     * List of books
     */
    private ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Binary path file
     */
    private final String inputFile;

    /**
     * Create instance
     * @param inputFile binary path file
     */
    public BookBinary(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Write a book to binary file
     *
     * @param book
     * @throws Exception
     */
    public void setBook(Book book) throws Exception {
        DataOutputStream dataOutputStream = openOutputStream(this.inputFile);
        dataOutputStream.writeUTF(book.title);
        dataOutputStream.writeUTF(book.author);
        dataOutputStream.writeInt(book.year);
        dataOutputStream.writeInt(book.ISBN);
        dataOutputStream.close();
    }

    /**
     * Get books from binary file
     *
     * @return Books
     */
    public ArrayList<Book> getBooks() throws Exception {
        // create buffer stream
        DataInputStream dataInputStream = openInputStream(this.inputFile);

        boolean isEOF = false;

        while (!isEOF) {
            try {
                String title = dataInputStream.readUTF();
                String author = dataInputStream.readUTF();
                int year = dataInputStream.readInt();
                int ISBN = dataInputStream.readInt();
                books.add(new Book(title, author, year, ISBN));
            } catch (EOFException e) {
                isEOF = true;
            }
        }

        // close stream
        dataInputStream.close();
        return books;
    }

    /**
     *
     * @param inputFile
     * @return
     * @throws Exception
     */
    private static DataOutputStream openOutputStream(String inputFile) throws Exception {
        File file = new File(inputFile);
        return new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file, true)));
    }

    /**
     * create stream data
     *
     * @param inputFile database path file
     * @return DataInputStream A data input stream instance
     * @throws Exception
     */
    private DataInputStream openInputStream(String inputFile) throws Exception {
        return new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
    }

}
