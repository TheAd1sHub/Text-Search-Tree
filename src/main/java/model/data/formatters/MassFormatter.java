package model.data.formatters;

import java.util.Collection;

public interface MassFormatter<TFormatted> {

    String formatAll(Collection<TFormatted> data);
}
