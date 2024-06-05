package model.data.formatters;

import model.core.searchtree.SearchResultData;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class SearchResultDataFormatter
        implements Formatter<SearchResultData>, MassFormatter<SearchResultData> {

    @Override
    public String format(@NotNull SearchResultData data) {
        if (data == null) {
            throw new IllegalArgumentException("Non-null value expected. null received.");
        }

        return "Found \"" + data.value + "\" at #" + data.index + ';';
    }

    @Override
    public String formatAll(@NotNull Collection<SearchResultData> data) {
        if (data == null) {
            throw new IllegalArgumentException("Non-null value expected. null received.");
        }

        if (data.isEmpty()) {
            return "No hits!";
        }


        StringBuilder buffer = new StringBuilder();
        for (SearchResultData formatted : data) {
            buffer.append(format(formatted)).append('\n');
        }

        return buffer.toString();
    }
}
