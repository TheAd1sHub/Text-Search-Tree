package model.net;

import debug.exceptions.HTTPReadingSessionFailException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Deprecated
public final class HTTPRequestSender {

    public HttpResponse<String> getResponseFromUrl(String pageUrl) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(pageUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response;

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            throw new HTTPReadingSessionFailException(ex, false);
        }
    }
}
