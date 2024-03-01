package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseInitService {


    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);

    public static void main(String[] args) {
        try {
            // Отримуємо підключення до бази даних
            Connection connection = Database.getInstance().getConnection();

            // Зчитуємо SQL-запити з файлу
            String sqlScript = readSqlScript("C:\\Users\\Toni\\IdeaProjects\\Lesson6JDBC\\src\\test\\resources\\init_db.sql");

            // Виконуємо запити
            executeSqlScript(connection, sqlScript);

            logger.info("Database initialized successfully.");
        } catch (SQLException | IOException e) {
            logger.error("Failed to initialize database.", e);
        }
    }

    private static String readSqlScript(String filePath) throws IOException {
        // Зчитуємо вміст SQL-скрипту з файлу
        Path path = Path.of(filePath);
        return Files.readString(path);
    }

    private static void executeSqlScript(Connection connection, String sqlScript) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {
            // Розділяємо SQL-скрипт на окремі запити
            String[] queries = sqlScript.split(";");

            // Виконуємо кожен окремий запит
            for (String query : queries) {
                preparedStatement.addBatch(query.trim());
            }

            // Виконуємо всі пакетні запити
                preparedStatement.executeBatch();
        }
    }
}