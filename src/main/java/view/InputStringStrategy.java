package view;

import java.util.function.Supplier;

public interface InputStringStrategy<T>{
    void prompt();
    T tryUserInputString(Supplier<String> userInputString);
}
