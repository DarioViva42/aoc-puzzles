package vivas.tk.adventofcode.day04;

import java.util.List;

public class OverlapFinder {

    private final List<Pair> pairs;

    public OverlapFinder(List<String> input) {
        this.pairs = input.stream()
                .map(Pair::new)
                .toList();
    }

    public int countFullOverlaps() {
        return (int) pairs.stream()
                .filter(Pair::hasFullOverlap)
                .count();
    }

    public int countPartialOverlaps() {
        return (int) pairs.stream()
                .filter(Pair::hasPartialOverlap)
                .count();
    }
}
