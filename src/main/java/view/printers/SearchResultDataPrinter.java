package view.printers;

import model.core.searchtree.SearchResultData;

import java.util.Collection;

public final class SearchResultDataPrinter
        implements Printer<SearchResultData>, MassPrinter<SearchResultData> {

    @Override
    public void printAll(Collection<SearchResultData> data) {
        if (data.size() == 0) {
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

        System.out.println("Found '" + data.value + "' at #" + data.index + '.');
    }


    private void printNoHitsMessage() {
        System.out.println("No hits!");
    }
}
