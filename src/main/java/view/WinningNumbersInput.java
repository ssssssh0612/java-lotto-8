package view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import model.domain.WinningNumbers;

public class WinningNumbersInput implements InputStringStrategy<WinningNumbers> {
    private static final String REGEX = "^(?!,)(?!.*,,)(.*[^,])?$";
    private static final Pattern ALLOWED_STRING = Pattern.compile(REGEX);
    private static final String ERR_MSG_INVALID_STRING_INPUT =
            "[ERROR] 올바른 , 형태의 문자열을 작성해주거나 올바른 int 범위의 숫자를 작성해주세요";
    private static final String MESSAGE_INPUT_WINNING_NUMBERS =
            "당첨 번호를 입력해 주세요.";

    @Override
    public void prompt() {
        System.out.println(MESSAGE_INPUT_WINNING_NUMBERS);
    }

    @Override
    public WinningNumbers tryUserInputString(Supplier<String> userInput) {
        while (true) {
            String inputString = userInput.get();
            try {
                requireInputStringAllowed(inputString);
                List<Integer> numbers = Arrays.stream(inputString.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toList();
                return new WinningNumbers(numbers);
            } catch (NumberFormatException e) {
                System.out.println(ERR_MSG_INVALID_STRING_INPUT);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void requireInputStringAllowed(String userInput){
        if (!ALLOWED_STRING.matcher(userInput).matches()) {
            throw new IllegalArgumentException(ERR_MSG_INVALID_STRING_INPUT);
        }
    }

}