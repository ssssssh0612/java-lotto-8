package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.domain.BonusNumber;
import model.domain.Lotto;
import model.domain.LottoRank;
import model.domain.WinningLotto;
import model.domain.WinningNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningLottoTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonus = new BonusNumber(7, winning);
        winningLotto = new WinningLotto(winning, bonus);
    }

    static Stream<Arguments> lottoCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), LottoRank.FIRST),

                Arguments.of(List.of(1, 2, 3, 4, 5, 7), LottoRank.SECOND),

                Arguments.of(List.of(1, 2, 3, 4, 5, 45), LottoRank.THIRD),

                Arguments.of(List.of(1, 2, 3, 4, 44, 45), LottoRank.FOURTH),

                Arguments.of(List.of(1, 2, 3, 43, 44, 45), LottoRank.FIFTH),

                Arguments.of(List.of(1, 2, 42, 43, 44, 45), LottoRank.NONE),
                Arguments.of(List.of(1, 41, 42, 43, 44, 45), LottoRank.NONE)
        );
    }

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("lottoCases")
    void 매칭_개수와_보너스_포함_여부웨_따라_올바른_등급을_반환한다(List<Integer> lottoNumbers, LottoRank expected) {
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(winningLotto.judgeLottoRank(lotto)).isEqualTo(expected);
    }
}
