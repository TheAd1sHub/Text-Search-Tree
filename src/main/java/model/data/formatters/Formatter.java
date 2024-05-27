package model.data.formatters;

public interface Formatter<TFormatted> {

    String format(TFormatted data);
}
