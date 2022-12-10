package vivas.tk.adventofcode.day04;

import java.util.List;

class OverlapFinder {

    private final List<Pair> pairs;

    public OverlapFinder(String input) {
        this.pairs = input.lines()
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
