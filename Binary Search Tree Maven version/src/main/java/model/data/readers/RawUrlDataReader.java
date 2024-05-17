package model.data.readers;

public final class RawUrlDataReader implements ExternalTextDataReader {
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
