package model.data.database.entries;

public abstract class DatabaseEntry {

    //public final String tableName;


    //public DatabaseEntry(String destinationTable) {
    //    tableName = destinationTable;
    //}


    public abstract String toSQL();

    @Override
    public String toString() {
        return toSQL();
    }
}
