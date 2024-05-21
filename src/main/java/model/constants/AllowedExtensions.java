package model.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AllowedExtensions {

    private static final String[] allowedExtensions = new String[] {
            ".txt", ".md", ""
    };

    private AllowedExtensions() {
        ;
    }

    public static List<String> getAllowedExtensionsList() {
    	
        return List.of(allowedExtensions);
    }
    
}
