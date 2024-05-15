package model.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public final class BorderedHttpResponseStreamReader
        implements AutoCloseable, Iterator<String> {
    private static final int DEFAULT_BUFFER_SIZE_CHARS = 1024;

    private final int bufferSizeChars;

    private final HttpResponseStreamReader reader;
    private final String leftBorder, rightBorder;

    private final StringBuilder buffer;

    private boolean reachedRightBorder = false;


    public BorderedHttpResponseStreamReader(InputStream inputStream, String leftBorder, String rightBorder,
                                            int bufferSizeChars) throws IOException, IllegalArgumentException {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;

        this.bufferSizeChars = bufferSizeChars;
        buffer = new StringBuilder(this.bufferSizeChars);

        reader = new HttpResponseStreamReader(inputStream);

        reachLeftBorder();
    }

    public BorderedHttpResponseStreamReader(InputStream inputStream, String leftBorder,
                                            String rightBorder) throws IOException, IllegalArgumentException {
        this(inputStream, leftBorder, rightBorder, DEFAULT_BUFFER_SIZE_CHARS);
    }


    private void reachLeftBorder() throws IllegalArgumentException {
        String currentLine;
        while (reader.hasNext() && buffer.indexOf(leftBorder) == -1) {

            currentLine = reader.next();

            if (currentLine.length() + buffer.length() > bufferSizeChars) {
                String possibleSubpattern = getCroppedStringPartFromBuffer(leftBorder);

                clearBuffer();
                buffer.append(possibleSubpattern);
            }

            buffer.append(currentLine);

            System.exit(228);
        }

        if (buffer.indexOf(leftBorder) == -1) {
            throw new IllegalArgumentException("The given stream data does not contain the given left border substring.");
        }
    }

    private String readNext() {
        String currentLine = next();

        if (currentLine.length() + buffer.length() > bufferSizeChars) {
            String possibleSubpattern = getCroppedStringPartFromBuffer(rightBorder);

            clearBuffer();
            buffer.append(possibleSubpattern);
            buffer.append(currentLine);
        }


        int indexOfOuterElement = buffer.indexOf(rightBorder);
        if (indexOfOuterElement == -1) {

            return currentLine;
        } else {
            reachedRightBorder = true;

            return currentLine.substring(0, indexOfOuterElement);
        }
    }

    private void clearBuffer() {
        buffer.setLength(0);
        buffer.setLength(bufferSizeChars);
    }

    private String getCroppedStringPartFromBuffer(String str) {
        for (int substringLength = str.length() - 1; substringLength >= 0; substringLength--) {
            String patternSubstring = str.substring(0, substringLength + 1);
            int indexOfLastOccurrence = buffer.lastIndexOf(patternSubstring);
            if (indexOfLastOccurrence == -1) {

                continue;
            }

            if (indexOfLastOccurrence + substringLength == buffer.length()) {

                return patternSubstring;
            }
        }

        return "";
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext() && !reachedRightBorder;
    }

    @Override
    public String next() {
        return readNext();
    }
}
