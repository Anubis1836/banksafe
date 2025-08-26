package com.wecp.progressive.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConnectionManager {

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    /**
     * Loads database configuration from application.properties into the properties object.
     */
    private static void loadProperties() {
        try (InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
    }

    /**
     * Creates and returns a JDBC connection using loaded database properties.
     */
    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        String driver = properties.getProperty("spring.datasource.driver-class-name");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver class not found", e);
        }

        return DriverManager.getConnection(url, username, password);
    }
}

