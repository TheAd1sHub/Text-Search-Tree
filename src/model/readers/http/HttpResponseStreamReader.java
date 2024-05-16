package model.readers.http;

import debug.exceptions.ReadingSessionFailException;
import debug.logs.MainLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class HttpResponseStreamReader
            implements AutoCloseable, Iterator<String> {

    private @Deprecated static final int DEFAULT_BUFFER_SIZE_CHARS = 1024;
    private @Deprecated static final int READING_STEP_CHARS = 32;

    private @Deprecated StringBuilder buffer;
    private @Deprecated int bufferSizeChars;

    private InputStream inputStream;

    BufferedReader reader;
    private String currentLine;

    // TODO: Find the size of a character dynamically depending on the encoding of opened webpage
    private @Deprecated int charSizeBytes = 1;

    private boolean hasReadAll = false;


    public HttpResponseStreamReader(InputStream inputStream) throws IOException {
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


    @Deprecated
    private void clearBuffer() {
        buffer.setLength(0);
        buffer.setLength(bufferSizeChars);
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
