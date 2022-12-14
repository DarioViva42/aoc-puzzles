package vivas.tk.adventofcode.day13;

import java.util.Arrays;
import java.util.List;

class DistressSignalFinder {

    List<PacketPair> pairs;

    public DistressSignalFinder(String input) {
        pairs = Arrays
                .stream(input.split("(\\n|\\r\\n){2}"))
                .map(PacketPair::parse)
                .toList();
    }

    public int evaluateCorrectlyOrderedPairs() {
        int score = 0;
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).isInOrder()) {
                score += i + 1;
            }
        }
        return score;
    }
}
