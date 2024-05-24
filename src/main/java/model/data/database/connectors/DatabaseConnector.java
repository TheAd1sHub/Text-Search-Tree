package model.data.database.connectors;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public abstract class DatabaseConnector {
    private final String jdbcUrl;

    private Connection connection;


    public DatabaseConnector(@NotNull String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }


    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection(this.jdbcUrl);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void execute(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public ResultSet executeAndGetResults(String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }
}
