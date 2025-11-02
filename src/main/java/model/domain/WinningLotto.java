package model.domain;

public class WinningLotto {
    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank judgeLottoRank(Lotto lotto) {
        int match = winningNumbers.countWinnerNumbers(lotto);
        boolean bonus = bonusNumber.isContainedIn(lotto);
        return LottoRank.of(match, bonus);
    }

}