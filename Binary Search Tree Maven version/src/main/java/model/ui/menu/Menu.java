package model.ui.menu;

import java.util.Map;

public abstract class Menu<TOption extends Enum<TOption>> {

    public final String title;
    public final Map<TOption, String> optionsNames;


    public Menu(String title, Map<TOption, String> optionsNames) {
        this.title = title;
        this.optionsNames = optionsNames;
    }

}
