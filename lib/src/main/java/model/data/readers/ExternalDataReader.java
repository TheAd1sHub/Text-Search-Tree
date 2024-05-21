package model.data.readers;

import java.io.Closeable;
import java.util.Iterator;

public interface ExternalDataReader<T> extends Iterator<T>, AutoCloseable {

}
