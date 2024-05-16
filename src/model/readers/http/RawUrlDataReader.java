package model.readers.http;

import model.net.UrlStreamReceiver;
import model.readers.ExternalTextDataReader;
import model.readers.iterators.HttpResponseStreamIterator;

import java.io.IOException;
import java.net.URL;

public final class RawUrlDataReader implements ExternalTextDataReader {
    private final UrlStreamReceiver receiver;
    private final HttpResponseStreamIterator streamReader;

    public RawUrlDataReader(URL pageUrl) throws IOException {
        receiver = new UrlStreamReceiver(pageUrl);
        streamReader = new HttpResponseStreamIterator(receiver.openStream());
    }

    public RawUrlDataReader(String pageUrl) throws IOException {
        this(new URL(pageUrl));
    }

    @Override
    public boolean hasNext() {

        return streamReader.hasNext();
    }

    @Override
    public String next() {

        return streamReader.next();
    }

    @Override
    public void close() throws Exception {
        receiver.close();
        streamReader.close();
    }

}
