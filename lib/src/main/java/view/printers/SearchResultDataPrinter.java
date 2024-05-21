package view.printers;

import model.core.searchtree.SearchResultData;

import java.util.Collection;

public final class SearchResultDataPrinter extends MassPrinter<SearchResultData> {

    @Override
    public void printAll(Collection<SearchResultData> data) {
        for (SearchResultData result : data) {
            print(result);
        }
    }

    @Override
    public void print(SearchResultData data) {
        if (data == null) {
            System.out.println("No hits!");

            return;
        }

        System.out.println("Found '" + data.value + "' at #" + data.index + '.');
    }

}
