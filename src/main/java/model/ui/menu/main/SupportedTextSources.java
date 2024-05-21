package model.ui.menu.main;

public enum SupportedTextSources {
    NONE(0, "NIL", false),
    LOCAL_FILE(1, "Local file", false),
    EXTERNAL_FILE(2, "External file", true),
    WEB_PAGE_CONTENTS(3, "Web page contents", true),
    WEB_PAGE_CONTENTS_RAW(4, "Web page contents (Raw)",  true);


    public final int value;
    public final String textVersion;
    public final boolean isWebSource;


    SupportedTextSources(int value, String textVersion, boolean isWebSource) {
        this.value = value;
        this.textVersion = textVersion;
        this.isWebSource = isWebSource;
    }

    public static SupportedTextSources getFittingEnumValue(int value) {
        for (SupportedTextSources sourceType : SupportedTextSources.values()) {
            if (sourceType.value == value) {

                return sourceType;
            }
        }

        return NONE;
    }
}
