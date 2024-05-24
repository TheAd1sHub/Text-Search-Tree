package model;

import model.data.database.connectors.concrete.SearchTreeDBConnector;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public final class RandomTests {

    @Test
    public void testSql() throws SQLException {
        SearchTreeDBConnector connector = SearchTreeDBConnector.getInstance();

        connector.init();
        connector.closeConnection();
    }
}
