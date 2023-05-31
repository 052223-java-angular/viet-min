/**
 * An interface that represents a screen for the application.
 * It defines a method that starts the screen and displays the user interface.
 */
package com.revature.app.screens;

import java.util.Scanner;

public interface IScreen {
    /**
     * The method that starts the screen and displays the user interface.
     * It takes a Scanner object as a parameter to get the user input.
     * @param scan a Scanner object for getting user input
     */
    void start(Scanner scan);
}
