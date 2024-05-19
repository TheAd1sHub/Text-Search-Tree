package model.ui.menu.main;

public enum TextSourcesOptions {
    LOCAL_FILE(1, "Local file"),
    EXTERNAL_FILE(2, "External file"),
    WEB_PAGE_CONTENTS(3, "Web page contents"),
    WEB_PAGE_CONTENTS_RAW(4, "Web page contents (Raw)");

    public final int value;
    public final String textVersion;

    TextSourcesOptions(int value, String textVersion) {
        this.value = value;
        this.textVersion = textVersion;
    }

}
