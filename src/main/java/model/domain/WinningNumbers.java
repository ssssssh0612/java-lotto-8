package model.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private final Lotto winningNumber;

    public WinningNumbers(List<Integer> numbers) {
        winningNumber = new Lotto(numbers);
    }

    public Iterator<Integer> getIterator() {
        return winningNumber.iterator();
    }

    public int countWinnerNumbers(Lotto lotto) {
        Set<Integer> winnerNumbers = new HashSet<>();
        for (Integer number : winningNumber) {
            winnerNumbers.add(number);
        }
        int count = 0;
        for (Integer number : lotto) {
            if (winnerNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }
}
