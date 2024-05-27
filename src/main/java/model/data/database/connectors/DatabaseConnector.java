package model.data.database.connectors;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public abstract class DatabaseConnector {
    protected final String jdbcUrl;

    protected Connection connection;


    public DatabaseConnector(@NotNull String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }


    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection(this.jdbcUrl);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void execute(@NotNull String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public ResultSet executeAndGetResults(@NotNull String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    public boolean doesTableExist(String tableName) throws SQLException {

        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});

        return resultSet.next();
    }
}
