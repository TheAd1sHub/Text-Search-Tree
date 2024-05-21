package model.data.loaders;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class UrlFileDownloader {

    private final String resultingFilePath;
    private final URL readingSourceUrl;


    public UrlFileDownloader(URL sourceUrl, String destinationPath) {
        resultingFilePath = destinationPath;
        readingSourceUrl = sourceUrl;
    }


    public void download() throws IOException {

        try (InputStream downloadStream = readingSourceUrl.openStream()) {
            Files.copy(downloadStream, Paths.get(resultingFilePath), StandardCopyOption.REPLACE_EXISTING);

        } catch (MalformedURLException | FileNotFoundException ex) {
            throw ex;
        }
    }
}
