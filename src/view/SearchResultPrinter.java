package view;

import java.util.Collection;

import model.SearchResultData;

public final class SearchResultPrinter {
    private SearchResultPrinter() {
        ;
    }

    public static void displayFormatted(SearchResultData data) {    	
    	if (data == null) {
    		displayNoHitsMessage();
    		
    		return;
    	}
    	
    	System.out.println("Found \'" + data.value + "' at #" + data.index + '.');
    }

    public static void displayFormatted(Collection<SearchResultData> dataSet) {
    	if (dataSet.isEmpty()) {
    		displayNoHitsMessage();
    		
    		return;
    	}

		System.out.println("Words found: " + dataSet.size() + ".");
    	
    	for (SearchResultData data : dataSet) {
    		displayFormatted(data);
    	}
    }
    
    private static void displayNoHitsMessage() {
    	System.out.println("No hits!");
    }
    
}