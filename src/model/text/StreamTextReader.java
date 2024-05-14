package model.text;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

// https://orionsystems1-my.sharepoint.com/:t:/g/personal/yury_serebryakov_orioninc_com/Ebasj5CBE4VPh2hcRKY823YBz8Miy7FTkTcFIUGzQBK3vw?e=pTOJnD
public final class StreamTextReader {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 1024;

    private StringBuilder buffer;
    private int bufferSizeBytes;

    private Scanner scanner;
    private Iterator<String> readStream;

    public StreamTextReader(Iterator<String> readStream,
                            int bufferSizeBytes) throws IllegalArgumentException {

        if (bufferSizeBytes <= 0) {
            throw new IllegalArgumentException("Buffer size cannot be '" + bufferSizeBytes + "'. Positive value expected.");
        }

        this.bufferSizeBytes = bufferSizeBytes;
        this.readStream = readStream;
        this.buffer = new StringBuilder(this.bufferSizeBytes);
    }

    public StreamTextReader(Iterator<String> readStream) {
        this(readStream, DEFAULT_BUFFER_SIZE_BYTES);
    }


    public void readInBuffer() {
        while (readStream.hasNext() && buffer.length() < bufferSizeBytes) {
            buffer.append(readStream.next());
        }
    }

    public String getBufferContents() {
        return buffer.toString();
    }

    public void clearBuffer() {
        buffer = new StringBuilder(bufferSizeBytes);
    }

    enum ReadingStatuses {

    }
}
