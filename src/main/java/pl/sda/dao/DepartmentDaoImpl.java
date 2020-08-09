package pl.sda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/zad0";
        return DriverManager.getConnection(jdbcUrl, "scott", "scott");
    }
}
