package com.tawan;

import java.io.*;
import java.util.ArrayList;

public class BookBinary {
    /**
     * List of books
     */
    public ArrayList<Book> Books = new ArrayList<Book>();

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
     *
     * @throws Exception
     */
    public void streamBooks() throws Exception {
        // create buffer stream
        DataInputStream dataInputStream = openInputStream(this.inputFile);

        boolean isEOF = false;
        while (!isEOF) {
            Book book = readDataInputStream(dataInputStream);
            if (book == null) {
                isEOF = true;
            } else {
                Books.add(book);
            }
        }

        // close stream
        dataInputStream.close();
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
        streamBooks();
        return Books;
    }


    /**
     * Read data from input stream
     * @param dataInputStream DataInputStream
     * @return Book
     */
    private static Book readDataInputStream(DataInputStream dataInputStream) {
        String title = null;
        String author = null;
        int year = 0;
        int ISBN = 0;

        try {
            title = dataInputStream.readUTF();
            author = dataInputStream.readUTF();
            year = dataInputStream.readInt();
            ISBN = dataInputStream.readInt();
        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            System.exit(0);
        }

        return new Book(title, author, year, ISBN);
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
