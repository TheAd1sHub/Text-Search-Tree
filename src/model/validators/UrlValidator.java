package model.validators;

import java.net.MalformedURLException;
import java.net.URL;

public final class UrlValidator extends Validator<String> {
    @Override
    public boolean isValid(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException ex) {

            return false;
        }

        return true;
    }
    
}
