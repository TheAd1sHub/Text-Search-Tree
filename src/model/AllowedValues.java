package model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public final class AllowedValues {
    private AllowedValues() {
        ;
    }

    private static final String[] allowedExtensions = new String[] {
        ".txt"
    };
    
    private static final Map<String, Boolean> closedQuestionsAnswers = new HashMap<>() {{
    	put("yes", true);
    	put("y", true);
    	put("no", true);
    	put("n", true);
    }};
    

    public static List<String> getAllowedExtensionsList() {
    	
        return List.of(allowedExtensions);
    }
    
    public static Map<String, Boolean>  getClosedQuestionsAnswersMap() {
    	
    	return closedQuestionsAnswers;
    }
    
}
