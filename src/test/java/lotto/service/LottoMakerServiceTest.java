package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.domain.Lottos;
import model.service.LottoMakerService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoMakerServiceTest {
    @Test
    void 요청한_개수만큼_생성해서_반환한다() {
        // given & when
        LottoMakerService lottoMakerService = new LottoMakerService();
        Lottos lottos = lottoMakerService.makeLottos(3);

        // then
        assertThat(lottos.getLottoCounts()).isEqualTo(3);
    }
}

