package model.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class HTTPTextReader {

    public void getFromUrl(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);



        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        }
    }
}
