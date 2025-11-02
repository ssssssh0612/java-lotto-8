package model.service;

import java.util.ArrayList;
import java.util.List;
import model.domain.Lotto;
import model.domain.LottoRank;
import model.domain.LottoRankResult;
import model.domain.Lottos;
import model.domain.WinningLotto;

public class LottoRankResultMakerService {
    public LottoRankResult makeLottoRankResult(WinningLotto winningLotto, Lottos lottos) {
        List<LottoRank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            LottoRank lottoRank = winningLotto.judgeLottoRank(lotto);
            ranks.add(lottoRank);
        }
        return LottoRankResult.of(ranks, lottos.getLottoCounts());
    }
}
