package lotto.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import model.domain.BonusNumber;
import model.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.BonusNumberInput;
import view.InputStringStrategy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BonusNumberInputTest {
    @ParameterizedTest(name = "[{index}] userInput: \"{0}\" -> retry then success")
    @ValueSource(strings = {"abc", "1_000", "1,000", "ㄱㄴㄷ", "ㅏㅑㅓ", ",./'?", "", " "})
    void 잘못된_입력_후_정상입력으로_성공한다(String userInput) {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        Queue<String> fakeUserInputs = new ArrayDeque<>(List.of(userInput, "25"));
        Supplier<String> mockInput = fakeUserInputs::poll;

        // when
        InputStringStrategy<BonusNumber> strategy = new BonusNumberInput(winningNumbers);
        BonusNumber result = strategy.tryUserInputString(mockInput);

        // then
        assertThat(result).isNotNull();
    }
}
