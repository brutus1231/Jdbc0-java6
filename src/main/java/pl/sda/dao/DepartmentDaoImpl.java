package pl.sda.dao;

import java.sql.*;

public class DepartmentDaoImpl {

    void printAll() {
        try (final Connection connection = getConnection()) {
            printAllData(connection);
        } catch (SQLException exp) {
            System.out.println("Błąd połączenia " + exp.getMessage());
        }
    }

    void printOne(Integer departmentId) {
        try (final Connection connection = getConnection()) {
            printOneDepartment(connection, departmentId);
        } catch (SQLException exp) {
            System.out.println("Błąd połączenia " + exp.getMessage());
        }
    }

    void insert(String name){
        try (final Connection connection = getConnection()) {
            insertOne(connection, name);
        } catch (SQLException exp) {
            System.out.println("Błąd połączenia " + exp.getMessage());
        }
    }

    private void insertOne(Connection connection, String name) throws SQLException {
        String query = "insert into department(name) values ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void printOneDepartment(Connection connection, Integer departmentId) throws SQLException {
        String query = "select * from department where department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, departmentId);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        printResult(resultSet);
    }

    private void printAllData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from department";
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        printResult(resultSet);
    }

    private void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int departmentId = resultSet.getInt("department_id");
            String departmentName = resultSet.getString("name");
            System.out.println("id: " + departmentId + " name: " + departmentName);
        }
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/zad0?autoReconnect=true&useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(jdbcUrl, "scott", "scott");
    }
}
