package model.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import model.domain.Lotto;
import model.domain.Lottos;

public class LottoMakerService {
    private static final int LOTTO_RANGE_START = 1;
    private static final int LOTTO_RANGE_END = 45;
    private static final int COUNT = 6;

    private static Lotto makeLotto() {
        List<Integer> numbers = Randoms
                .pickUniqueNumbersInRange(LOTTO_RANGE_START, LOTTO_RANGE_END, COUNT)
                .stream()
                .sorted()
                .toList();
        return new Lotto(numbers);
    }

    public Lottos makeLottos(int count) {
        List<Lotto> generated = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            generated.add(makeLotto());
        }
        return new Lottos(generated);
    }
}
