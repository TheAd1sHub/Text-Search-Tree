package model.data.loaders;

import java.io.IOException;

public interface Loader {

    

    default void loadInto(String destinationPath) throws IOException {

    }
}
