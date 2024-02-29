package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class Database {

    private static final String URL = "jdbc:h2:mem:testDb"; // URL з'єднання з H2, можна змінити
    private static final String USER = "а1";
    private static final String PASSWORD = System.getenv("password");

    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Додаємо синхронізацію до методу getInstance()
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}


