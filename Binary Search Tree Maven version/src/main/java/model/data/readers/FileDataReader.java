package model.data.readers;

import debug.logging.MainLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class FileDataReader implements ExternalTextDataReader {

    private final FileReader fileReader;
    private final BufferedReader bufferedReader;


    public FileDataReader(File file) throws IOException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
    }

    public FileDataReader(String filePath) throws IOException {
        this(new File(filePath));
    }


    @Override
    public boolean hasNext() {
        try {

            return bufferedReader.ready();
        } catch (IOException ex) {
            MainLogger.logSevere(ex);

            return false;
        }
    }

    @Override
    public String next() {
        try {

            return bufferedReader.readLine();
        } catch (IOException ex) {
            MainLogger.logSevere(ex);

            return null;
        }
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
        fileReader.close();
    }
}