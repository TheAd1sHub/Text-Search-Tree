package view.printers;

import java.util.Collection;

public interface MassPrinter<TPrinted> {

    void printAll(Collection<TPrinted> printed);

}
