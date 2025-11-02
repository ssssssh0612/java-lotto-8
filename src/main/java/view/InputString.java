package view;

import java.util.function.Supplier;

public class InputString<T> {
    private final InputStringStrategy<T> strategy;

    public InputString(InputStringStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public T getInputString(Supplier<String> userInputString) {
        strategy.prompt();
        return strategy.tryUserInputString(userInputString);
    }
}
