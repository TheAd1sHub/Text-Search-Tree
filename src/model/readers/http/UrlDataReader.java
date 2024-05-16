package model.readers.http;

import model.net.UrlStreamReceiver;
import model.readers.ExternalTextDataReader;
import model.readers.iterators.BorderedHttpResponseStreamIterator;
import model.readers.iterators.HttpResponseStreamIterator;

import java.io.IOException;
import java.net.URL;

public final class UrlDataReader implements ExternalTextDataReader {
    private final UrlStreamReceiver receiver;
    private final BorderedHttpResponseStreamIterator streamReader;

    private String leftBorder, rightBorder;

    public UrlDataReader(URL pageUrl) throws IOException {
        receiver = new UrlStreamReceiver(pageUrl);
        streamReader = new BorderedHttpResponseStreamIterator(
                receiver.openStream(), leftBorder, rightBorder);
    }

    public UrlDataReader(String pageUrl) throws IOException {
        this(new URL(pageUrl));
    }


    @Override
    public void close() throws Exception {
        streamReader.close();;
    }

    @Override
    public boolean hasNext() {
        return streamReader.hasNext();
    }

    @Override
    public String next() {
        return streamReader.next();
    }
}
