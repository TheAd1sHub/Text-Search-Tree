package view.printers;

import java.util.Collection;

public abstract class MassPrinter<TPrinted> extends Printer<TPrinted> {

    public abstract void printAll(Collection<TPrinted> printed);

}
