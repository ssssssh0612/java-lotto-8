package lotto.service;

import java.util.List;
import model.domain.BonusNumber;
import model.domain.Lotto;
import model.domain.LottoRankResult;
import model.domain.Lottos;
import model.domain.WinningLotto;
import model.domain.WinningNumbers;
import model.service.LottoRankResultMakerService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static model.domain.LottoRank.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoRankResultMakerServiceTest {

    private final LottoRankResultMakerService lottoRankResultMakerService = new LottoRankResultMakerService();

    @Test
    void 로또_번호와_당첨번호를_비교해_알맞은_결과가_나오는지() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Lotto first = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto third = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        Lotto fourth = new Lotto(List.of(1, 2, 3, 4, 44, 45));
        Lotto fifth = new Lotto(List.of(1, 2, 3, 43, 44, 45));

        // given
        Lottos lottos = new Lottos(List.of(first, second, third, fourth, fifth));

        // when
        LottoRankResult result = lottoRankResultMakerService.makeLottoRankResult(winningLotto, lottos);

        // then
        assertThat(result.countOf(FIRST)).isEqualTo(1);
        assertThat(result.countOf(SECOND)).isEqualTo(1);
        assertThat(result.countOf(THIRD)).isEqualTo(1);
        assertThat(result.countOf(FOURTH)).isEqualTo(1);
        assertThat(result.countOf(FIFTH)).isEqualTo(1);
    }
}