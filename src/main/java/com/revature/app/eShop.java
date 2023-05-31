/**
 * This class represents the main entry point for the eShop application.
 * It uses the RouterServices class to handle the navigation between different views,
 * and the SessionUtil class to manage the user session.
 * It also uses a Scanner object to read user input from the console,
 * and a Logger object to log messages to a file.
 */
package com.revature.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.app.services.RouterServices;
import com.revature.app.utils.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class eShop {
  // A Logger object to log messages to a file
  private static final Logger log = LogManager.getLogger(eShop.class);

  /**
   * The main method of the eShop application.
   * It creates a Scanner object to read user input from the console,
   * and a RouterServices object to handle the navigation between different views.
   * It then calls the navigate method of the RouterServices object with the "/home" path as the initial view,
   * and passes the Scanner object as an argument.
   * It finally closes the Scanner object and logs a message indicating the end of the application.
   * @param args The command-line arguments (not used)
   * @throws ClassNotFoundException If the JDBC driver class is not found
   * @throws IOException If an I/O error occurs
   * @throws SQLException If a database error occurs
   */
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    // Log a message indicating the start of the application
    log.info("Start eShop app");

    // Create a Scanner object to read user input from the console
    Scanner scan = new Scanner(System.in);

    // Create a RouterServices object to handle the navigation between different views
    // Pass a new SessionUtil object and null as arguments to the constructor
    RouterServices route = new RouterServices(new SessionUtil(), null);

    // Call the navigate method of the RouterServices object with the "/home" path as the initial view
    // Pass the Scanner object as an argument
    route.navigate("/home", scan);

    // Close the Scanner object
    scan.close();

    // Log a message indicating the end of the application
    log.info("End eShop app");
  }
}