package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Delegate<T extends Supplier<T>> {
    private List<Supplier<T>> subscriptions = new ArrayList<>();

    public void subscribe(Supplier<T> function) {
        if (!subscriptions.contains(function)) {
            subscriptions.add(function);
        }
    }

    public void unsubscribe(Supplier<T> function) {
        subscriptions.remove(function);
    }

    public T invoke() {
        T result = null;

        for (Supplier<T> function : subscriptions) {
            result = function.get();
        }

        return result;
    }
}
