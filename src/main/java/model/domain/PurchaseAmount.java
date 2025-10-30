package model.domain;

import java.util.regex.Pattern;

public class PurchaseAmount {
    private final int amount;
    private static int MIN_LEN = 4;
    private static int MAX_LEN = 9;
    private static final int LOTTO_PRICE = 1000;
    private static final String REGEX = "^[0-9]+$";
    private static final Pattern ONLY_NUMBER = Pattern.compile(REGEX);
    private static final String ERR_MSG_INPUT_ONLY_NUMBER = "[ERROR] 숫자만 입력해주세요";
    private static final String ERR_MSG_ENTER_VALID_NUMBER_IN_RANGE = "[ERROR] 범위 내의 올바른 숫자를 입력해주세요";

    public PurchaseAmount(int userInput) {
        this.amount = userInput;
    }

    public static PurchaseAmount from(String userInput) {
        validate(userInput);
        return new PurchaseAmount(Integer.parseInt(userInput));
    }

    public static void validate(String userInput) {
        requireOnlyNumber(userInput);
        requireLengthBetween(userInput);
        requireDivisibleByLottoPrice(userInput);
        
    }

    public static void requireOnlyNumber(String userInput) {
        if (!ONLY_NUMBER.matcher(userInput).matches()) {
            throw new IllegalArgumentException(ERR_MSG_INPUT_ONLY_NUMBER);
        }
    }

    public static void requireLengthBetween(String userInput) {
        int length = userInput.length();
        if (length < MIN_LEN || length > MAX_LEN) {
            throw new IllegalArgumentException(ERR_MSG_ENTER_VALID_NUMBER_IN_RANGE);
        }
    }

    public static void requireDivisibleByLottoPrice(String userInput) {
        if (Integer.parseInt(userInput) % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERR_MSG_ENTER_VALID_NUMBER_IN_RANGE);
        }
    }

    public int purchasableCount() {
        return amount / LOTTO_PRICE;
    }
}
