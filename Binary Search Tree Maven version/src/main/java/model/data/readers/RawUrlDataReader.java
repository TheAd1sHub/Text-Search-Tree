package model.data.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public final class RawUrlDataReader implements ExternalTextDataReader {

    private final URL sourceUrl;

    private InputStream inputStream;
    private BufferedReader bufferedReader;

    private String currentLine;
    private boolean hasReadAll = false;


    public RawUrlDataReader(URL sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public RawUrlDataReader(String url) throws MalformedURLException {
        this(new URL(url));
    }


    public void openReadingStream() throws IOException {
         if (inputStream != null) {
             throw new UnsupportedOperationException();
         }

         inputStream = sourceUrl.openStream();
         bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void closeReadingStream() throws IOException {
        if (inputStream == null) {
            throw new UnsupportedOperationException();
        }

        inputStream = null;
        bufferedReader.close();
    }

    public String readNextLine() throws IOException {

        if (bufferedReader == null) {
            openReadingStream();
        }

        currentLine = bufferedReader.readLine();
        if (currentLine == null) {
            hasReadAll = true;
        }

        return currentLine;
    }


    @Override
    public boolean hasNext() {

        return !hasReadAll;
    }

    @Override
    public String next() {
        try {
            String line = currentLine;
            readNextLine();

            return line;
        } catch (IOException e) {

            throw new RuntimeException();
        }
    }

    @Override
    public void close() throws Exception {

        closeReadingStream();
    }
}
