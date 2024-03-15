package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class DatabasePopulateService {
    private static final Logger logger = Logger.getLogger(DatabasePopulateService.class.getName());

    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            // Read SQL queries from the file
            String sqlScript = readSqlScript("C:\\Users\\Toni\\IdeaProjects\\Lesson6JDBC\\src\\test\\resources\\populate_db.sql");

            // Execute the queries
            addSqlScriptToBatch(connection, sqlScript);
            logger.info("Database populated successfully.");

            // Populate different tables
            DatabasePopulateService populateService = new DatabasePopulateService();
            List<PersonEntity> workers = null; // Initialize with actual data
            workers.add(new PersonEntity(1, "John Doe", new Date(90, 0, 15), "Trainee", 800));
            workers.add(new PersonEntity(2, "Jane Smith", new Date(85, 4, 20), "Junior", 1200));
            workers.add(new PersonEntity(3, "Alice Johnson", new Date(88, 8, 10), "Senior", 2000));

            List<Client> clients = null;
            clients.add(new Client(1, "John Doe"));
            clients.add(new Client(2, "Jane Smith"));

            List<Projects> projects = null;
            projects.add(new Projects(1, 12345, new Date(85, 0, 15), new Date(85, 4, 20), 800));
            projects.add(new Projects(2, 123456, new Date(85, 4, 20), new Date(86, 5, 20), 1200));
            projects.add(new Projects(3, 1234567, new Date(85, 8, 10), new Date(87, 6, 20), 2000));

            List<ProjectsList> projectsLists = null;
            projectsLists.add(new ProjectsList(12345, 1234567));
            projectsLists.add(new ProjectsList(98765, 987654));

            populateService.insertWorkers(workers);
            populateService.insertClients(clients);
            populateService.insertProjects(projects);
            populateService.assignWorkersToProjects(projectsLists);
            logger.info("Database populated successfully.");
        } catch (SQLException | IOException e) {
            logger.severe("Error while populating the database: " + e.getMessage());
        }
    }

    private static String readSqlScript(String filePath) throws IOException {
        // Read the content of SQL script from the file
        Path path = Path.of(filePath);
        return Files.readString(path);
    }

    private static void addSqlScriptToBatch(Connection connection, String sqlScript) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlScript)) {
            // Split the SQL script into separate queries
            String[] queries = sqlScript.split(";");

            // Execute each individual query
            for (String query : queries) {
                // Trim the query and check if it's not empty
                query = query.trim();
                if (!query.isEmpty()) {
                    // Add the query to the batch
                    preparedStatement.addBatch(query);
                }
            }

            // Execute all batched queries
            preparedStatement.executeBatch();
        }
    }

    public void insertWorkers(List<PersonEntity> personEntities) throws SQLException {
        String query = "INSERT INTO worker (ID, NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (PersonEntity personEntity : personEntities) {
                preparedStatement.setInt(1, personEntity.getId());
                preparedStatement.setString(2, personEntity.getName());
                preparedStatement.setDate(3, personEntity.getBirthday());
                preparedStatement.setString(4, personEntity.getLevel());
                preparedStatement.setInt(5, personEntity.getSalary());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("Worker data inserted successfully.");
        } catch (SQLException e) {
            logger.severe("Error while inserting worker data: " + e.getMessage());
            throw e;
        }
    }

    public void insertClients(List<Client> clients) throws SQLException {
        String query = "INSERT INTO client (ID, NAME) VALUES (?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Client client : clients) {
                preparedStatement.setInt(1, client.getId());
                preparedStatement.setString(2, client.getName());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("Client data inserted successfully.");
        } catch (SQLException e) {
            logger.severe("Error while inserting client data: " + e.getMessage());
            throw e;
        }
    }

    public void insertProjects(List<Projects> projects) throws SQLException {
        String query = "INSERT INTO project (ID, CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Projects project : projects) {
                preparedStatement.setInt(1, project.getId());
                preparedStatement.setInt(2, project.getClientId());
                preparedStatement.setDate(3, project.getStartDate());
                preparedStatement.setDate(4, project.getFinishDate());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("Project data inserted successfully.");
        } catch (SQLException e) {
            logger.severe("Error while inserting project data: " + e.getMessage());
            throw e;
        }
    }

    public void assignWorkersToProjects(List<ProjectsList> projectsLists) throws SQLException {
        String query = "INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES(?, ?) ";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (ProjectsList projectsList : projectsLists) {
                preparedStatement.setInt(1, projectsList.getProjectId());
                preparedStatement.setInt(2, projectsList.getWorkerId());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("Project data inserted successfully.");
        } catch (SQLException e) {
            logger.severe("Error while inserting project data: " + e.getMessage());
            throw e;
        }
    }
}


