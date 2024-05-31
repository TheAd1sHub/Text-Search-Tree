package model.data.database.entries;

import java.sql.Date;

// CREATE TABLE Searches(search_date DATE, token VARCHAR, source VARCHAR, result VARCHAR);
public final class SearchRequestEntry extends DatabaseEntry {

    public Date date;
    public String token;
    public String source;
    public String result;
    public int id;


    public SearchRequestEntry() {
        super();
    }

    public SearchRequestEntry(String destinationTableName, Date searchDate, String soughtForToken,
                                        String searchSource, String searchResult, int entryId) {
        //super(destinationTableName);

        date = searchDate;
        token = soughtForToken;
        source = searchSource;
        result = searchResult;
        id = entryId;
    }

    @Override
    public String toSQL() {
        return "('" + date.toString() + "', '" +
                token + "', '" + source + "','" +
                result + "', " + id + ")";
    }
}
