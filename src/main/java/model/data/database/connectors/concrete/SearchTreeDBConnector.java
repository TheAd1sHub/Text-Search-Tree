package model.data.database.connectors.concrete;

import model.data.database.connectors.SQLiteConnector;
import model.data.database.entries.SearchRequestEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SearchTreeDBConnector extends SQLiteConnector {

    public static final String SEARCHTREE_DB_PATH = "C:\\searchtree.sqlite";
    public static final String MAIN_TABLE_NAME = "Searches";

    private static final SQLiteConnector dbConnector = new SQLiteConnector(SEARCHTREE_DB_PATH);

    private static SearchTreeDBConnector instance;

    private SearchRequestEntry nextEntry;


    static {
        //try {
        //    Class.forName("org.sqlite.jdbc3-");
        //} catch (ClassNotFoundException e) {
        //
        //    throw new RuntimeException(e);
        //}
    }

    private SearchTreeDBConnector() {
        super(SEARCHTREE_DB_PATH);
    }

    public static synchronized SearchTreeDBConnector getInstance() {

        if (instance == null) {
            instance = new SearchTreeDBConnector();
        }

        return instance;
    }


    public void init() throws SQLException {
        openConnection();

        String tableExistenceCheckQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + MAIN_TABLE_NAME + "';\n";

        ResultSet tableSearchResults = executeAndGetResults(tableExistenceCheckQuery);
        if (tableSearchResults.wasNull()) {
            createTable();
        }
        tableSearchResults.close();

    }

    public void createTable() throws SQLException {
        execute("CREATE TABLE " + MAIN_TABLE_NAME +"(search_date DATE, token VARCHAR, source VARCHAR, result VARCHAR);");
    }

    public void addEntry(SearchRequestEntry entry) throws SQLException {
        execute("INSERT INTO " + MAIN_TABLE_NAME + " VALUES " + entry.toSQL() + ";");
    }

    public void createNewEntry() {
        nextEntry = new SearchRequestEntry();
    }

    public SearchRequestEntry getConstructedEntry() {

        return nextEntry;
    }
}
