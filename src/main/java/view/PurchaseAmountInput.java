package view;

import java.util.function.Supplier;
import model.domain.PurchaseAmount;

public class PurchaseAmountInput implements InputStringStrategy<PurchaseAmount> {
    private static final String ERR_MSG_INVALID_NUMBER_INPUT =
            "[ERROR] 올바른 형태의 숫자를 작성하거나, int 범위 내의 숫자를 작성해주세요";
    private static final String MESSAGE_INPUT_PURCHASE_AMOUNT =
            "구입금액을 입력해 주세요.";

    @Override
    public void prompt() {
        System.out.println(MESSAGE_INPUT_PURCHASE_AMOUNT);
    }

    @Override
    public PurchaseAmount tryUserInputString(Supplier<String> userInput) {
        while (true) {
            String inputString = userInput.get();
            try {
                int purchaseAmount = Integer.parseInt(inputString);
                return new PurchaseAmount(purchaseAmount);
            } catch (NumberFormatException e) {
                System.out.println(ERR_MSG_INVALID_NUMBER_INPUT);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}