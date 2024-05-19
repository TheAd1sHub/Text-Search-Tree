package view.printers;

public abstract class Printer<TMessage> {

    public abstract void print(TMessage message);
}
