package model;

import model.data.database.connectors.concrete.SearchTreeDBConnector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Optional;

public final class RandomTests {

    @Test
    public void testSql() throws SQLException {
        SearchTreeDBConnector connector = SearchTreeDBConnector.getInstance();

        connector.init();
        connector.closeConnection();
    }

    @Test
    public void testOptional() {
        assertDoesNotThrow(() -> Optional.ofNullable(null));
        assertThrows(NullPointerException.class, () -> Optional.of(null));
    }
}
