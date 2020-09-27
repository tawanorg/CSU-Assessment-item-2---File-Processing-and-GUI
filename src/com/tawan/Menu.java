package com.tawan;

import java.util.Scanner;

public class Menu {

    public void startOver(Scanner scanner, Boolean startProgram) {
        // Give the user an option to re-access the main menu
        // once they have completed their search.
        System.out.println("Return to main menu! Y/N: ");
        String goToMainMenu = scanner.nextLine();
        if (goToMainMenu.equals("Y")) {
            startProgram = false;
        }
    }

    public void makeMenu() {

    }
}
