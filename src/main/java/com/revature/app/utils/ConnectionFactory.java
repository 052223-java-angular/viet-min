/**
 * A utility class that creates and manages a connection to the database using the singleton design pattern.
 * It uses the org.postgresql.Driver class to register the driver and the DriverManager class to get the connection.
 * It also uses a properties file to store the database url, username and password.
 */
package com.revature.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;

    /**
     * A private constructor that creates a new connection to the database using the properties file.
     * It loads the driver class and gets the url, username and password from the properties file.
     * It then uses the DriverManager to get the connection and assigns it to the connection field. 
     * @throws ClassNotFoundException If the driver class is not found.
     * @throws IOException If the properties file is not found or cannot be read.
     * @throws SQLException If the connection cannot be established. 
     */
    private ConnectionFactory() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        connection = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password"));
    }

    /* ----------------- Methods ----------------- */

    /**
     * A public static method that returns an instance of this class with an active connection.
     * It checks if the instance field is null or if the connection is closed, and creates a new instance if so.
     * It then returns the instance field. 
     * @return A ConnectionFactory object with an active connection. 
     * @throws SQLException If the connection cannot be established or checked for closure. 
     * @throws ClassNotFoundException If the driver class is not found.
     * @throws IOException If the properties file is not found or cannot be read. 
     */
    public static ConnectionFactory getInstance() throws SQLException, ClassNotFoundException, IOException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    /**
     * A public method that returns the connection field of this class.
     * @return A Connection object representing the connection to the database. 
     */
    public Connection getConnection() {
        return connection;
    }

    /* ----------------- Helper Methods ----------------- */

    /**
     * A private method that loads and returns the properties from the application.properties file.
     * It uses an InputStream object to read the file from the classpath and loads it into a Properties object.
     * It then returns the Properties object. 
     * @return A Properties object containing the database url, username and password. 
     * @throws IOException If the properties file is not found or cannot be read. 
     */
    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            props.load(input);
        }
        return props;
    }
}