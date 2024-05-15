package model.net;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public final class FullScopeHttpResponseStreamReader {

    public FullScopeHttpResponseStreamReader(String url) throws IOException {

        String contents = Jsoup.connect(url).get().body().data();
        System.out.println("'" + contents + "'");
    }
}
