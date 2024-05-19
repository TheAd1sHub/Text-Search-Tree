package model.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class UrlStreamReceiver implements AutoCloseable {

    private final URL sourceUrl;

    private InputStream openedStream;


    public UrlStreamReceiver(URL url) {
        sourceUrl = url;
    }


    public InputStream openStream() throws IOException {
        openedStream = sourceUrl.openStream();

        return openedStream;
    }

    public void closeStream() throws Exception {
        openedStream.close();
    }


    @Override
    public void close() throws Exception {
        closeStream();
    }
}
