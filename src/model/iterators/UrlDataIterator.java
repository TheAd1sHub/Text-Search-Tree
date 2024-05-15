package model.iterators;

import model.net.HttpResponseStreamReader;
import model.net.UrlStreamReceiver;

import java.io.IOException;
import java.net.URL;

public class UrlDataIterator implements ExternalDataIterator {

    private final RawUrlDataIterator iterator;


    public UrlDataIterator(URL pageUrl) throws IOException {
        iterator = new RawUrlDataIterator(pageUrl);
    }

    public UrlDataIterator(String pageUrl) throws IOException {
        this(new URL(pageUrl));
    }


    @Override
    public void close() throws Exception {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return "";
    }
}
