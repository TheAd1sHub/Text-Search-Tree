package model;

import java.util.Objects;

public final class SearchResultData {
    public final String value;
    public final int index;

    public SearchResultData(String contents, int index) {
        this.value = contents;
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SearchResultData) {
            SearchResultData other = (SearchResultData) obj;

            return this.value.equals(other.value) && this.index == other.index;
        }

        return false;
    }
}