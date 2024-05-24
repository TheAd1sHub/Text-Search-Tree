package model.data.database.entries;

import java.sql.Date;

// CREATE TABLE Searches(search_date DATE, token VARCHAR, source VARCHAR, result VARCHAR);
public final class SearchRequestEntry extends DatabaseEntry {

    public Date date;
    public String token;
    public String source;
    public String result;


    public SearchRequestEntry() {
        super();
    }

    public SearchRequestEntry(String destinationTableName, Date searchDate, String soughtForToken,
                                        String searchSource, String searchResult) {
        //super(destinationTableName);

        date = searchDate;
        token = soughtForToken;
        source = searchSource;
        result = searchResult;
    }

    @Override
    public String toSQL() {
        return "('" + date.toString() + "', '" +
                token + "', '" + source + "', '" +
                result + "')";
    }
}
