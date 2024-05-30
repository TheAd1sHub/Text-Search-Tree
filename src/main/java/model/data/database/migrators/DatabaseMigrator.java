package model.data.database.migrators;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DatabaseMigrator<TMigrator> {

    public DatabaseMigrator(@NotNull String dataSource,
                          @Nullable String username,
                          @Nullable String password) { }

    public abstract TMigrator getMigrator();
}
