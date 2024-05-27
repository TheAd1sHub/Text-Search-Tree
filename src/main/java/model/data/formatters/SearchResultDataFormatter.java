package model.data.formatters;

import model.core.searchtree.SearchResultData;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class SearchResultDataFormatter
        implements Formatter<SearchResultData>, MassFormatter<SearchResultData> {

    @Override
    public String format(@NotNull SearchResultData data) {


        return "Found \"" + data.value + "\" at #" + data.index + ';';
    }

    @Override
    public String formatAll(@NotNull Collection<SearchResultData> data) {

        StringBuilder buffer = new StringBuilder();
        for (SearchResultData formatted : data) {
            buffer.append(format(formatted)).append('\n');
        }

        return buffer.toString();
    }
}
