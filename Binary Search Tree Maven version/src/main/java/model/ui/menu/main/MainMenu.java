package model.ui.menu.main;

import model.ui.menu.Menu;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public final class MainMenu extends Menu<TextSourcesOptions> {

    private static MainMenu instance;

    public MainMenu(String title, Map<TextSourcesOptions, String> optionsNames) {
        super(title, optionsNames);
    }

    public static synchronized MainMenu getInstance() {

        if (instance == null) {
            instance = new MainMenu(
                    "THE ULTIMATE TEXT PARSER v. M4V3N",
                    new HashMap<>() {
                        {
                            put(TextSourcesOptions.LOCAL_FILE, TextSourcesOptions.LOCAL_FILE.textVersion);
                            put(TextSourcesOptions.EXTERNAL_FILE, TextSourcesOptions.EXTERNAL_FILE.textVersion);
                            put(TextSourcesOptions.WEB_PAGE_CONTENTS, TextSourcesOptions.WEB_PAGE_CONTENTS.textVersion);
                            put(TextSourcesOptions.WEB_PAGE_CONTENTS_RAW, TextSourcesOptions.WEB_PAGE_CONTENTS_RAW.textVersion);
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

        for (TextSourcesOptions option : EnumSet.allOf(TextSourcesOptions.class)) {
            sb.append(option.value);
            sb.append(". ");
            sb.append(optionsNames.get(option));
            sb.append('\n');
        }

        cachedToString = sb.toString();

        return cachedToString;
    }

}
