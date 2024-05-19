package model.readers.iterators;

import debug.exceptions.ReadingSessionFailException;
import debug.logs.MainLogger;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class HttpResponseStreamIterator
            implements AutoCloseable, Iterator<String> {
    private InputStream inputStream;

    BufferedReader reader;
    private String currentLine;

    private boolean hasReadAll = false;


    public HttpResponseStreamIterator(@NotNull InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        reader = new BufferedReader(new InputStreamReader(this.inputStream));

        readNextLine();
    }


    public String readNextLine() throws IOException {

        currentLine = reader.readLine();
        if (currentLine == null) {
            hasReadAll = true;
        }

        return currentLine;
    }

    @Override
    public void close() throws IOException {
        reader.close();
        inputStream.close();
    }

    @Override
    public boolean hasNext() {

        return !hasReadAll;
    }

    @Override
    public String next() {
        try {
            String line = currentLine;
            currentLine = readNextLine();

            return line;
        } catch (IOException ex) {
            MainLogger.logSevere(
                    new ReadingSessionFailException(
                            "An exception occurred while reading the URL contents!",
                            ex
                    )
            );

            hasReadAll = true;
        }

        return null;
    }
}
