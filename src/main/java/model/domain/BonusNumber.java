package model.domain;

import java.util.Iterator;

public class BonusNumber {
    private static final String ERR_MSG_LOTTO_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1~45 사이입니다";
    private static final String ERR_MSG_BONUS_NUMBER_DUPLICATION = "[ERROR] 보너스 번호가 당첨번호와 중복됩니다";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private final int number;

    public BonusNumber(int userInput, WinningNumbers winningNumbers) {
        validate(userInput, winningNumbers);
        this.number = userInput;
    }

    private static void validate(int userInput, WinningNumbers winningNumbers) {
        requireNumberRange(userInput);
        requireNotContainedInWinningNumbers(userInput, winningNumbers);
    }

    private static void requireNumberRange(int inputNumbers) {
        if (inputNumbers < LOTTO_MIN_NUMBER || inputNumbers > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ERR_MSG_LOTTO_NUMBER_OUT_OF_RANGE);
        }
    }

    private static void requireNotContainedInWinningNumbers(int userInput, WinningNumbers winningNumbers) {
        if (isContainedIn(userInput, winningNumbers)) {
            throw new IllegalArgumentException(ERR_MSG_BONUS_NUMBER_DUPLICATION);
        }
    }

    private static boolean isContainedIn(int userInput, WinningNumbers winningNumbers) {
        Iterator<Integer> lottoIter = winningNumbers.getIterator();

        while (lottoIter.hasNext()) {
            if (isSame(lottoIter.next(), userInput)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSame(int compareNumber, int userInput) {
        return compareNumber == userInput;
    }

    private boolean isSame(int compareNumber) {
        return compareNumber == number;
    }

    public boolean isContainedIn(Lotto lotto) {
        for (Integer integer : lotto) {
            if (isSame(integer)) {
                return true;
            }
        }

        return false;
    }
}
