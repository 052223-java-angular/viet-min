package com.revature.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPostgres {
    private static ConnectionPostgres instance;
    private Connection connection;

    private ConnectionPostgres() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
                props.getProperty("password"));
    }

    public static ConnectionPostgres getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionPostgres();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private Properties getProperties() throws IOException {
        Properties props = new Properties();

        try (InputStream iStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (iStream == null) {
                throw new IOException("Unable to find application.properties");
            }
            props.load(iStream);
        }

        return props;
    }
}
