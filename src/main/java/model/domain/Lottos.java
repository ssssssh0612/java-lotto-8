package model.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> values;

    public Lottos(List<Lotto> values) {
        this.values = List.copyOf(values);
    }

    public int getLottoCounts() {
        return values.size();
    }

    @Override
    public String toString() {
        return values.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Iterator<Lotto> iterator() {
        return values.iterator();
    }

}
