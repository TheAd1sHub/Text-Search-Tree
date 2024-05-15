package model.iterators;

public final class FileDataIterator implements ExternalDataIterator {



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
