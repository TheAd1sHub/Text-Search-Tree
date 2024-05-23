package view.printers;

import model.ui.menu.Menu;

public final class MenuPrinter implements Printer<Menu> {

    @Override
    public void print(Menu menu) {

        System.out.println(menu.toString());
    }

}
