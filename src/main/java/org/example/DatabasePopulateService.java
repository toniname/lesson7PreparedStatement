package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabasePopulateService {

    private static final Logger logger = Logger.getLogger(DatabasePopulateService.class.getName());

    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            // Зчитуємо SQL-запити з файлу
            String sqlScript = readSqlScript("C:\\Users\\Toni\\IdeaProjects\\Lesson6JDBC\\src\\test\\resources\\populate_db.sql");

            // Виконуємо запити
            addSqlScriptToBatch(connection, sqlScript);

            logger.info("Database populated successfully.");
        } catch (SQLException | IOException e) {
            logger.severe("Error while populating the database: " + e.getMessage());
        }
    }

    private static String readSqlScript(String filePath) throws IOException {
        // Зчитуємо вміст SQL-скрипту з файлу
        Path path = Path.of(filePath);
        return Files.readString(path);
    }

    private static void addSqlScriptToBatch(Connection connection, String sqlScript) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
            // Розділяємо SQL-скрипт на окремі запити
            String[] queries = sqlScript.split(";");

            // Виконуємо кожен окремий запит
            for (String query : queries) {
                statement.addBatch(query.trim());
            }

            // Виконуємо всі пакетні запити
            statement.executeBatch();
        }
    }
}