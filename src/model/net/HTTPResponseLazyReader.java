package model.net;

import debug.exceptions.HTTPReadingSessionFailException;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

@Deprecated
public final class HTTPResponseLazyReader
                            implements Iterator<String>, AutoCloseable {
    private final BufferedReader reader;
    private String nextLine;

    private final InputStream inputStream;

    public HTTPResponseLazyReader(HttpResponse<String> response) throws IOException {
        reader = new BufferedReader(new StringReader(response.body()));
        getNextLine();

        inputStream = new ByteArrayInputStream(response.body().getBytes());
    }

    private void getNextLine() throws IOException {
        nextLine = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return nextLine != null;
    }

    @Override
    public String next() {
        if (nextLine == null) {
            throw new NoSuchElementException();
        }

        String line = nextLine;

        try {
            getNextLine();
        } catch (IOException e) {
            throw new HTTPReadingSessionFailException(e);
        }
        return line;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }
}
