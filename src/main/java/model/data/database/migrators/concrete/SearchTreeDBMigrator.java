package model.data.database.migrators.concrete;


import model.data.database.migrators.FlywayMigrator;

public final class SearchTreeDBMigrator extends FlywayMigrator {

    public static final String SEARCHES_DB_PATH = "jdbc:sqlite:searchtree.db";

    private static SearchTreeDBMigrator instance;


    private SearchTreeDBMigrator() {
        super(SEARCHES_DB_PATH, null, null);
    }

    public static synchronized SearchTreeDBMigrator getInstance() {
        if (instance == null) {
            instance = new SearchTreeDBMigrator();
        }

        return instance;
    }
}
