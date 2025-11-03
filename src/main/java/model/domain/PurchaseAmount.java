package model.domain;

public class PurchaseAmount {
    private static final String LN = System.lineSeparator();
    private static final int LOTTO_PRICE = 1000;
    private static final int ZERO = 0;
    private static final String ERR_MSG_ENTER_VALID_NUMBER_IN_RANGE = "[ERROR] 1000보다 큰 숫자이거나, 1000으로 나누어 떨어지는 숫자를 입력해주세요";
    private static final String ERR_MSG_NUMBER_MUST_BE_POSITIVE = "[ERROR] 숫자는 0보다 커야합니다";
    private static final String PURCHASED_MESSAGE = "개를 구매했습니다.";
    private final int amount;

    public PurchaseAmount(int userInput) {
        validate(userInput);
        this.amount = userInput;
    }

    private static void validate(int userInput) {
        requirePositiveNumber(userInput);
        requireDivisibleByLottoPrice(userInput);
    }

    private static void requirePositiveNumber(int value) {
        if (value <= ZERO) {
            throw new IllegalArgumentException(ERR_MSG_NUMBER_MUST_BE_POSITIVE);
        }
    }

    private static void requireDivisibleByLottoPrice(int userInput) {
        if (userInput % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERR_MSG_ENTER_VALID_NUMBER_IN_RANGE);
        }
    }

    public int purchasableCount() {
        return amount / LOTTO_PRICE;
    }

    @Override
    public String toString() {
        return LN + purchasableCount() + PURCHASED_MESSAGE;
    }
}
