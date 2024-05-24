package model.data.database.connectors;

import java.sql.DriverManager;

public class SQLiteConnector extends DatabaseConnector {
    private static final String SQLITE_JDBC_BASE_URL = "jdbc:sqlite:";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public SQLiteConnector(String dbPath) {
        super(SQLITE_JDBC_BASE_URL + dbPath);
    }
}
