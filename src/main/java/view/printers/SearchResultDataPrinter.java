package view.printers;

import model.core.searchtree.SearchResultData;
import model.data.formatters.SearchResultDataFormatter;

import java.util.Collection;

public final class SearchResultDataPrinter
        implements Printer<SearchResultData>, MassPrinter<SearchResultData> {

    private final SearchResultDataFormatter formatter = new SearchResultDataFormatter();

    @Override
    public void printAll(Collection<SearchResultData> data) {
        if (data.isEmpty()) {
            printNoHitsMessage();
            
            return;
        }

        for (SearchResultData result : data) {
            print(result);
        }
    }

    @Override
    public void print(SearchResultData data) {
        if (data == null) {
            printNoHitsMessage();

            return;
        }

        System.out.println(formatter.format(data));
    }


    private void printNoHitsMessage() {
        System.out.println("No hits!");
    }
}
