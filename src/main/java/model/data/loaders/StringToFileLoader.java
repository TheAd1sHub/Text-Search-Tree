package model.data.loaders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public final class StringToFileLoader {

    private final Iterator<String> dataIterator;


    public StringToFileLoader(String data) {
        this.dataIterator = new Iterator<String>() {
            private String contents = data;
            private boolean wasStringRead = false;

            @Override
            public boolean hasNext() {

                return !wasStringRead;
            }

            @Override
            public String next() {
                wasStringRead = true;

                return contents;
            }
        };
    }

    public StringToFileLoader(Iterable<String> dataIterable) {
        this.dataIterator = dataIterable.iterator();
    }

    public StringToFileLoader(Iterator<String> dataIterator) {
        this.dataIterator = dataIterator;
    }


    public void loadInto(String destinationFileName, boolean overwriteIfExists) throws IOException {
        File desinationFile = new File(destinationFileName);

        if (Files.exists(Path.of(destinationFileName)) && overwriteIfExists) {
            desinationFile.delete();
            desinationFile.createNewFile();
        }

        while (dataIterator.hasNext()) {
            Files.writeString(desinationFile.toPath(), dataIterator.next());
        }
    }

    public void loadInto(String destinationFileName) throws IOException {
        loadInto(destinationFileName, false);
    }

}
