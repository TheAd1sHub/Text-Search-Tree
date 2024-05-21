package view;

import java.io.IOException;

public final class Display {

    private Display() {
        ;
    }


    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
