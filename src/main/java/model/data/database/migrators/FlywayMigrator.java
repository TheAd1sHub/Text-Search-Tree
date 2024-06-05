package model.data.database.migrators;

import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlywayMigrator extends DatabaseMigrator<Flyway> {

    protected final Flyway flyway;


    public FlywayMigrator(@NotNull String dataSource,
                          @NotNull String workingDirectory,
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
                .locations(workingDirectory)
                .dataSource(dataSource, username, password)
                //.validateMigrationNaming(true) // uncomment to see which scripts are named against the convention
                .load();
    }

    @Override
    public Flyway getMigrator() {
        return flyway;
    }

}
