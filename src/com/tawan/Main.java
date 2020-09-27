package com.tawan;

public class Main {
    public static void main(String[] args) throws Exception {
        // Binary data path file
        String fileData = "data.dat";

        // Create home library application instance
        HomeLibrary homeLibrary = new HomeLibrary(fileData);

        // Start program
        homeLibrary.start();
    }
}
