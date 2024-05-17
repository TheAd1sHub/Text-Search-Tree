package model.ui.menu.main;

import model.ui.menu.Menu;

import java.util.EnumSet;
import java.util.Map;

public final class MainMenu extends Menu<TextSources> {


    public MainMenu(String title, Map<TextSources, String> optionsNames) {
        super(title, optionsNames);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(title);
        sb.append('\n');

        for (TextSources option : EnumSet.allOf(TextSources.class)) {
            sb.append(option.toString());
            sb.append('\n');
        }

        return sb.toString();
    }

}
