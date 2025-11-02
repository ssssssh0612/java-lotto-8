package lotto;

import camp.nextstep.edu.missionutils.Console;
import model.domain.BonusNumber;
import model.domain.LottoRankResult;
import model.domain.Lottos;
import model.domain.PurchaseAmount;
import model.domain.WinningLotto;
import model.domain.WinningNumbers;
import model.service.LottoMakerService;
import model.service.LottoRankResultMakerService;
import view.BonusNumberInput;
import view.InputString;
import view.OutputView;
import view.PurchaseAmountInput;
import view.WinningNumbersInput;

public class Application {
    public static void main(String[] args) {
        InputString<PurchaseAmount> purchaseAmountInput = new InputString<>(new PurchaseAmountInput());
        PurchaseAmount purchaseAmount = purchaseAmountInput.getInputString(Console::readLine);
        OutputView.printView(purchaseAmount);
        LottoMakerService lottoMakerService = new LottoMakerService();
        Lottos lottos = lottoMakerService.makeLottos(purchaseAmount.purchasableCount());
        OutputView.printView(lottos);
        InputString<WinningNumbers> winningNumbersInput = new InputString<>(new WinningNumbersInput());
        WinningNumbers winningNumbers = winningNumbersInput.getInputString(Console::readLine);
        InputString<BonusNumber> bonusNumberInput = new InputString<>(new BonusNumberInput(winningNumbers));
        BonusNumber bonusNumber = bonusNumberInput.getInputString(Console::readLine);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoRankResultMakerService lottoRankResultMakerService = new LottoRankResultMakerService();
        LottoRankResult lottoRankResult = lottoRankResultMakerService.makeLottoRankResult(winningLotto, lottos);
        OutputView.printView(lottoRankResult);
    }
}
