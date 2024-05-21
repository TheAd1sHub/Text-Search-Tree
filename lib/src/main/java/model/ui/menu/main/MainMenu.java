package model.ui.menu.main;

import model.ui.menu.Menu;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public final class MainMenu extends Menu<SupportedTextSources> {

    private static MainMenu instance;

    public MainMenu(String title, Map<SupportedTextSources, String> optionsNames) {
        super(title, optionsNames);
    }

    public static synchronized MainMenu getInstance() {

        if (instance == null) {
            instance = new MainMenu(
                    "THE ULTIMATE TEXT PARSER v. M4V3N",
                    new HashMap<>() {
                        {
                            put(SupportedTextSources.LOCAL_FILE, SupportedTextSources.LOCAL_FILE.textVersion);
                            put(SupportedTextSources.EXTERNAL_FILE, SupportedTextSources.EXTERNAL_FILE.textVersion);
                            put(SupportedTextSources.WEB_PAGE_CONTENTS, SupportedTextSources.WEB_PAGE_CONTENTS.textVersion);
                            put(SupportedTextSources.WEB_PAGE_CONTENTS_RAW, SupportedTextSources.WEB_PAGE_CONTENTS_RAW.textVersion);
                        }
                    }
            );
        }

        return instance;
    }

    @Override
    public String toString() {
        if (cachedToString != null) {

            return cachedToString;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(title);
        sb.append('\n');

        for (SupportedTextSources option : EnumSet.allOf(SupportedTextSources.class)) {
            if (option == SupportedTextSources.NONE) {

                continue;
            }

            sb.append(option.value);
            sb.append(". ");
            sb.append(optionsNames.get(option));
            sb.append('\n');
        }

        cachedToString = sb.toString();

        return cachedToString;
    }

}
