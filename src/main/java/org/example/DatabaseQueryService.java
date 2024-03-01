package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseQueryService {

    private static final Logger logger = Logger.getLogger(DatabaseQueryService.class.getName());

    public List<MaxProjectCountClient> findMaxProjectsClient() throws IOException {
        String sqlQuery = readSqlScript("find_max_projects_client.sql");
        return executeMaxProjectCountClientQuery(sqlQuery);
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException {
        String sqlQuery = readSqlScript("find_max_salary_worker.sql");
        return executeMaxSalaryWorkerQuery(sqlQuery);
    }

    public List<LongestProject> findLongestProject() throws IOException {
        String sqlQuery = readSqlScript("find_longest_project.sql");
        return executeLongestProject(sqlQuery);
    }

    public List<YoungestEldestWorker> findYoungestEldestWorker() throws IOException {
        String sqlQuery = readSqlScript("find_youngest_eldest_workers.sql");
        return executeYoungestEldestWorker(sqlQuery);
    }
    public List<PrintProjectPrices> findProjectPrices() throws IOException {
        String sqlQuery = readSqlScript("print_project_prices.sql");
        return executePrintProjectPrices(sqlQuery);
    }

    private List<MaxProjectCountClient> executeMaxProjectCountClientQuery(String sqlQuery) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            return parseMaxProjectCountClients(resultSet);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing max project count client query", e);
        }
        return new ArrayList<>();
    }

    private List<MaxSalaryWorker> executeMaxSalaryWorkerQuery(String sqlQuery) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            return parseMaxSalaryWorker(resultSet);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing max salary worker query", e);
        }
        return new ArrayList<>();
    }

    private List<LongestProject> executeLongestProject(String sqlQuery) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            return parseLongestProject(resultSet);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing longest project query", e);
        }
        return new ArrayList<>();
    }

    private List<YoungestEldestWorker> executeYoungestEldestWorker(String sqlQuery) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            return parseYoungestEldestWorker(resultSet);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing youngest eldest worker query", e);
        }
        return new ArrayList<>();
    }
    private List<PrintProjectPrices> executePrintProjectPrices(String sqlQuery) {
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery(sqlQuery)) {

            return parsePrintProjectPrices(resultSet);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing print project price query", e);
        }
        return new ArrayList<>();
    }

    private List<MaxProjectCountClient> parseMaxProjectCountClients(ResultSet resultSet) throws SQLException {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int projectCount = resultSet.getInt("projectCount");
            maxProjectCountClients.add(new MaxProjectCountClient(name, projectCount));
        }
        return maxProjectCountClients;
    }

    private List<MaxSalaryWorker> parseMaxSalaryWorker(ResultSet resultSet) throws SQLException {
        List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int maxSalary = resultSet.getInt("salary");
            maxSalaryWorkers.add(new MaxSalaryWorker(name, maxSalary));
        }
        return maxSalaryWorkers;
    }

    private List<LongestProject> parseLongestProject(ResultSet resultSet) throws SQLException {
        List<LongestProject> longestProject = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int monthsBetween = resultSet.getInt("monthsBetween");
            longestProject.add(new LongestProject(name, monthsBetween));
        }
        return longestProject;
    }

    private List<YoungestEldestWorker> parseYoungestEldestWorker(ResultSet resultSet) throws SQLException {
        List<YoungestEldestWorker> youngestEldestWorker = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int birthday = resultSet.getInt("birthday");
            youngestEldestWorker.add(new YoungestEldestWorker(name, birthday));
        }
        return youngestEldestWorker;
    }
    private List<PrintProjectPrices> parsePrintProjectPrices(ResultSet resultSet) throws SQLException {
        List<PrintProjectPrices> printProjectPrices = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int sum = resultSet.getInt("sum");
            printProjectPrices.add(new PrintProjectPrices(name, sum));
        }
        return printProjectPrices;
    }

    private String readSqlScript(String fileName) throws IOException {
        Path path = Path.of("C:\\Users\\Toni\\IdeaProjects\\Lesson6JDBC\\src\\test\\resources\\" + fileName);
        return Files.readString(path);
    }
}
