package model.readers.filework;

import debug.exceptions.ReadingSessionFailException;

import java.io.*;
import java.nio.file.Files;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class InputStreamFileDownloader implements Closeable, AutoCloseable {

    private final InputStream downloadStream;
    private final String resultingFilePath;

    public InputStreamFileDownloader(InputStream sourceStream, String destinationPath) {
        downloadStream = sourceStream;
        resultingFilePath = destinationPath;
    }

    public void download() {

        try {
            Files.copy(downloadStream, Paths.get(resultingFilePath), StandardCopyOption.REPLACE_EXISTING);

        } catch (MalformedURLException ex) {
            throw new ReadingSessionFailException("The given URL is not valid", ex);
        } catch (FileNotFoundException ex) {
            throw new ReadingSessionFailException("Unable to create a file in a given destination!", ex);
        } catch (IOException ex) {
            throw new ReadingSessionFailException("The reading session failed due to an unknown reason!", ex);
        }
    }

    @Override
    public void close() throws IOException {
        downloadStream.close();
    }
}
