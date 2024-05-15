package model.net;

import debug.exceptions.HTTPReadingSessionFailException;
import debug.logs.MainLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public final class HttpResponseStreamReader
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
    /*
    public HttpResponseStreamReader(URL pageUrl, int bufferSizeChars) {
        try {
            inputStream = pageUrl.openStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            readNextLine();

            // Deprecated methods stuff
            this.bufferSizeChars = bufferSizeChars;
            buffer = new StringBuilder(this.bufferSizeChars);

        } catch (IOException ex) {
            MainLogger.logSevere(
                    new HTTPReadingSessionFailException(
                            "Unable to initialize object due to I/O Exception",
                            ex
                    )
            );
        }

    }

    public HttpResponseStreamReader(URL pageUrl) {
        this(pageUrl, DEFAULT_BUFFER_SIZE_CHARS);
    }


    @Deprecated
    public String getNextLine() throws IOException {
        if (!hasNext()) {
            throw new IOException("Nothing left to read!");
        }

        byte[] nextBytes;
        int indexOfLineEnd = -1;
        String result = "";
        do {
            nextBytes = inputStream.readNBytes(READING_STEP_CHARS * charSizeBytes);

            if (nextBytes.length == 0) {
                hasReadAll = true;
            }

            buffer.append(new String(nextBytes).intern());
            indexOfLineEnd = buffer.indexOf("\n");

            if (indexOfLineEnd == -1) {
                indexOfLineEnd = buffer.indexOf("\0");
            }

            // In case we've already filled the buffer...
            if (buffer.length() + READING_STEP_CHARS * charSizeBytes > bufferSizeChars) {
                result += buffer.toString();
                clearBuffer();
            }

        } while(indexOfLineEnd == -1 && !hasReadAll);

        result += buffer.toString();

        String currentLine = result.substring(0, indexOfLineEnd + 1);

        String remainingChars = "";
        if (indexOfLineEnd < result.length() -1) {
            remainingChars = result.substring(indexOfLineEnd + 1);
        }

        clearBuffer();
        buffer.append(remainingChars);

        return currentLine;
    }
    */

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
                    new HTTPReadingSessionFailException(
                            "An exception occurred while reading the URL contents!",
                            ex
                    )
            );
        }

        return "";
    }
}
