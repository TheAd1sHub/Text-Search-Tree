package model.data.database.migrators;

import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlywayMigrator extends DatabaseMigrator<Flyway> {

    private final Flyway flyway;


    public FlywayMigrator(@NotNull String dataSource,
                          @Nullable String username,
                          @Nullable String password) {

        super(dataSource, username, password);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        flyway = Flyway
                .configure()
                .dataSource(dataSource, username, password)
                .load();
    }

    @Override
    public Flyway getMigrator() {
        return flyway;
    }

}
