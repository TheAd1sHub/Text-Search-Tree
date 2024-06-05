PRAGMA foreign_keys=OFF;

ALTER TABLE Searches
RENAME TO Searches_old;

CREATE TABLE Searches (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT ROWID,
    search_date DATE,
    token VARCHAR NOT NULL,
    source VARCHAR NOT NULL,
    result VARCHAR
);

INSERT INTO Searches (id, search_date, token, source, result)
SELECT id, search_date, token, source, result
FROM Searches_old;

PRAGMA foreign_keys=ON;
