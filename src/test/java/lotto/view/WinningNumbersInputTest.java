package lotto.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;
import model.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputStringStrategy;
import view.WinningNumbersInput;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningNumbersInputTest {
    @ParameterizedTest(name = "[{index}] userInput: \"{0}\" -> retry then success")
    @ValueSource(strings = {
            "1,2,3,4,5,6,,",
            ",,1,2,3,4,5,6",
            "1,,2,,3,,4,,5,,6",
            ",,,,"})
    void 잘못된_입력_후_정상입력으로_성공한다(String userInput) {
        // given
        Queue<String> fakeUserInputs = new ArrayDeque<>(List.of(userInput, "1,2,3,4,5,6"));
        Supplier<String> mockInput = fakeUserInputs::poll;
        Set<Integer> mockCompare = new HashSet<>();

        // when
        InputStringStrategy<WinningNumbers> strategy = new WinningNumbersInput();
        WinningNumbers result = strategy.tryUserInputString(mockInput);
        result.getIterator().forEachRemaining(mockCompare::add);

        // then
        assertEquals(Set.of(1, 2, 3, 4, 5, 6), mockCompare);
    }
}
