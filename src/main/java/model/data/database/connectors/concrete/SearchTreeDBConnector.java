package model.data.database.connectors.concrete;

import model.data.database.connectors.SQLiteConnector;
import model.data.database.entries.SearchRequestEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;

public final class SearchTreeDBConnector extends SQLiteConnector {

    public static final String SEARCHTREE_DB_PATH = "searchtree.db";
    public static final String MAIN_TABLE_NAME = "Searches";


    private static SearchTreeDBConnector instance;

    private SearchRequestEntry nextEntry;
    private int entriesCount;


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

        String rowsCountCheckQuery = "SELECT Count(" + "'id'" + ") FROM " + MAIN_TABLE_NAME + ";";
        ResultSet rowsCountResult = executeAndGetResults(rowsCountCheckQuery);
        entriesCount = ((Number) rowsCountResult.getObject(1)).intValue();
        rowsCountResult.close();


        //String tableExistenceCheckQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + MAIN_TABLE_NAME + "';\n";

        //ResultSet tableSearchResults = executeAndGetResults(tableExistenceCheckQuery);
        //if (!doesTableExist(MAIN_TABLE_NAME)) {
        //    createTable();
        //}

        //tableSearchResults.close();

    }

    //public void createTable() throws SQLException {
    //    execute("CREATE TABLE " + MAIN_TABLE_NAME + " (search_date DATE, token VARCHAR, source VARCHAR, result VARCHAR);");
    //}

    public void addEntry(SearchRequestEntry entry) throws SQLException {
        entry.id = ++entriesCount;
        String query = "INSERT INTO " + MAIN_TABLE_NAME + " (search_date, token, source, result, id)" +
                " VALUES " + entry.toSQL() + ";";
        execute(query);
    }

    public void createNewEntry() {
        nextEntry = new SearchRequestEntry();
    }

    public SearchRequestEntry getConstructedEntry() {

        return nextEntry;
    }
}
