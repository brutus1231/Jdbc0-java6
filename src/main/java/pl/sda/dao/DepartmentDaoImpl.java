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
        String jdbcUrl = "jdbc:mysql://localhost:3306/zad0";
        return DriverManager.getConnection(jdbcUrl, "scott", "scott");
    }
}
