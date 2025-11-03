package view;

import java.util.function.Supplier;
import model.domain.BonusNumber;
import model.domain.WinningNumbers;

public class BonusNumberInput implements InputStringStrategy<BonusNumber>{
    private static final String ERR_MSG_INVALID_NUMBER_INPUT =
            "[ERROR] 올바른 형태의 숫자를 작성하거나, int 범위 내의 숫자를 작성해주세요";
    private static final String MESSAGE_INPUT_BONUS_NUMBER =
            "보너스 번호를 입력해 주세요.";
    private final WinningNumbers winningNumbers;

    public BonusNumberInput(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    @Override
    public void prompt() {
        System.out.println();
        System.out.println(MESSAGE_INPUT_BONUS_NUMBER);
    }

    @Override
    public BonusNumber tryUserInputString(Supplier<String> userInput) {
        while(true){
            String inputString = userInput.get();
            try{
                int value = Integer.parseInt(inputString);
                return new BonusNumber(value, winningNumbers);
            }catch (NumberFormatException e) {
                System.out.println(ERR_MSG_INVALID_NUMBER_INPUT);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}