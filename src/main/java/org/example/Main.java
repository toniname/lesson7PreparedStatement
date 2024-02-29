package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Отримання екземпляру класу Database
        Database database = Database.getInstance();


        // Отримання з'єднання з БД
        try (Connection connection = database.getConnection()) {
            // логіка роботи з БД тут
        } catch (SQLException e) {
            e.printStackTrace();
            // Обробка помилок роботи з БД
        }
    }
}