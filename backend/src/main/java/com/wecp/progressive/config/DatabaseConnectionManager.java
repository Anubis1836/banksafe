package com.wecp.progressive.config;
 
 
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;
 
public class DatabaseConnectionManager {
    private static final Properties properties = new Properties();
    static{
        loadProperties();
    }
    private static void loadProperties(){
        if(properties.isEmpty()){
            try(InputStream input = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")){
                if(input==null){
                    throw new IllegalStateException("resource.properties not found in classpath");
                }
                properties.load(input);
            }catch(IOException e){
                throw new RuntimeException("Error loading properties file", e);
            }
        }
    }
    public static Connection getConnection() throws SQLException{
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        return DriverManager.getConnection(url, username, password);
    }
    public static void initializeDatabase(){
        try(Connection conn = getConnection();
            Statement st = conn.createStatement()){
            st.executeUpdate("CREATE TABLE IF NOT EXISTS customers ("+
            "customer_id INT AUTO_INCREMENT PRIMARY KEY,"+
            "name VARCHAR(255) NOT NULL,"+
            "email VARCHAR(255) NOT NULL,"+
            "username VARCHAR(255) NOT NULL,"+
            "password VARCHAR(255) NOT NULL,"+
            "role VARCHAR(255))");
 
            st.executeUpdate("CREATE TABLE IF NOT EXISTS accounts ("+
            "account_id INT AUTO_INCREMENT PRIMARY KEY,"+
            "customer_id INT NOT NULL,"+
            "balance DECIMAL(10,2) NOT NULL)");
 
            st.executeUpdate("CREATE TABLE IF NOT EXISTS transactions("+
            "transaction_id INT AUTO_INCREMENT PRIMARY KEY,"+
            "account_id INT NOT NULL,"+
            "amount DECIMAL(10,2) NOT NULL,"+
            "transaction_date TIMESTAMP NOT NULL,"+
            "transaction_type VARCHAR(255) NOT NULL)");
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
 
 