package view.printers;

public final class NativePrinter implements Printer<String> {

    static {
        System.loadLibrary("native_printer");
    }

    public native void print(String string);
}
