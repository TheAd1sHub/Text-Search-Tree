package model.iterators;

import model.net.HttpResponseStreamReader;
import model.net.UrlStreamReceiver;

import java.io.IOException;
import java.net.URL;

public final class RawUrlDataIterator implements ExternalDataIterator {
    private final UrlStreamReceiver receiver;
    private final HttpResponseStreamReader streamReader;


    public RawUrlDataIterator(URL pageUrl) throws IOException {
        receiver = new UrlStreamReceiver(pageUrl);
        streamReader = new HttpResponseStreamReader(receiver.openStream());
    }

    public RawUrlDataIterator(String pageUrl) throws IOException {
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
