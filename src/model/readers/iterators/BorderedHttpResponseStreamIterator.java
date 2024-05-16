package model.readers.iterators;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.util.Iterator;

public final class BorderedHttpResponseStreamIterator
        implements AutoCloseable, Iterator<String> {
    private static final int DEFAULT_BUFFER_SIZE_CHARS = 1024;

    private final int bufferSizeChars;

    private final HttpResponseStreamIterator reader;
    private final String leftBorder, rightBorder;

    private final StringBuilder buffer;

    private boolean reachedRightBorder = false;


    public BorderedHttpResponseStreamIterator(InputStream inputStream, @NotNull String leftBorder, String rightBorder,
                                              int bufferSizeChars) throws IOException, IllegalArgumentException {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;

        if (bufferSizeChars <= 0) {
            throw new IllegalArgumentException("The size of a buffer cannot be (" + bufferSizeChars + "). Positive number expected!");
        }

        this.bufferSizeChars = bufferSizeChars;
        buffer = new StringBuilder(this.bufferSizeChars);

        reader = new HttpResponseStreamIterator(inputStream);

        reachLeftBorder();
    }

    public BorderedHttpResponseStreamIterator(InputStream inputStream, @NotNull String leftBorder,
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
        }

        int leftBorderBeginningIndex = buffer.indexOf(leftBorder);
        if (leftBorderBeginningIndex == -1) {
            throw new IllegalArgumentException("The data from the given stream does not contain the required left border substring.");
        }


        String textAfterLeftBorder = "";

        if (leftBorderBeginningIndex + leftBorder.length() != buffer.length()) {
            textAfterLeftBorder = buffer.substring(leftBorderBeginningIndex + leftBorder.length());
        }

        clearBuffer();
        buffer.append(textAfterLeftBorder);

    }

    private String readNext() {
        String currentLine = reader.next();

        if (currentLine.length() + buffer.length() > bufferSizeChars) {
            String possibleRightBorderPart = getCroppedStringPartFromBuffer(rightBorder);

            if (bufferSizeChars - possibleRightBorderPart.length() < currentLine.length()) {
                throw new BufferOverflowException();
            }

            clearBuffer();
            buffer.append(possibleRightBorderPart);
        }

        buffer.append(currentLine);

        int indexOfRightBorderElement = buffer.indexOf(rightBorder);
        if (indexOfRightBorderElement == -1 || rightBorder.isBlank()) {

            return currentLine;
        } else {
            reachedRightBorder = true;



            return buffer.substring(0, indexOfRightBorderElement);
        }
    }

    private void clearBuffer() {
        buffer.setLength(0);
        //buffer.setLength(bufferSizeChars);
    }

    private String getCroppedStringPartFromBuffer(@NotNull String pattern) {
        for (int substringLength = pattern.length() - 1; substringLength >= 0; substringLength--) {
            String patternSubstring = pattern.substring(0, substringLength + 1);
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
