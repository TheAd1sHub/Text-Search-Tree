package model.readers.filework;

import debug.logs.MainLogger;
import model.readers.iterators.ExternalTextDataIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class FileDataStreamReader implements ExternalTextDataIterator {
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    public FileDataStreamReader(File file) throws IOException {
        this.fileReader = new FileReader(file);
        this.bufferedReader = new BufferedReader(fileReader);
    }

    public FileDataStreamReader(String filePath) throws IOException {
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