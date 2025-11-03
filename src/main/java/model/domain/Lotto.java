package model.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Lotto implements Iterable<Integer> {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final String ERR_MSG_LOTTO_SIZE_MUST_BE_SIX = "[ERROR] 로또 번호는 6개여야 합니다";
    private static final String ERR_MSG_LOTTO_NUMBERS_MUST_BE_UNIQUE = "[ERROR] 로또 번호는 고유해야 합니다";
    private static final String ERR_MSG_LOTTO_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1~45 사이입니다";
    private final List<Integer> numbers;

    public Lotto(List<Integer> inputNumbers) {
        validate(inputNumbers);
        this.numbers = List.copyOf(inputNumbers);
    }

    private static void validate(List<Integer> inputNumbers) {
        requireSixNumbers(inputNumbers);
        requireDistinctNumbers(inputNumbers);
        requireNumbersRange(inputNumbers);
    }

    private static void requireSixNumbers(List<Integer> inputNumbers) {
        if (inputNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERR_MSG_LOTTO_SIZE_MUST_BE_SIX);
        }
    }

    private static void requireDistinctNumbers(List<Integer> inputNumbers) {
        if (new HashSet<>(inputNumbers).size() != inputNumbers.size()) {
            throw new IllegalArgumentException(ERR_MSG_LOTTO_NUMBERS_MUST_BE_UNIQUE);
        }
    }

    private static void requireNumbersRange(List<Integer> inputNumbers) {
        for (Integer number : inputNumbers) {
            if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
                throw new IllegalArgumentException(ERR_MSG_LOTTO_NUMBER_OUT_OF_RANGE);
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return numbers.iterator();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
