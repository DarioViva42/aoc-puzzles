package tk.vivas.adventofcode.year2023.day15;

import java.util.Arrays;

class AsciiStringHelperAlgorithm {

    private final String[] words;

    AsciiStringHelperAlgorithm(String [] words) {
        this.words = words;
    }

    long hashSum() {
        return Arrays.stream(words)
                .mapToLong(this::hash)
                .sum();
    }

    int hash(String string) {
        return hash(string, 0);
    }

    private int hash(String string, int currentValue) {
        if (string.isEmpty()) {
            return currentValue;
        }
        currentValue = 17 * (string.charAt(0) + currentValue) % 256;
        return hash(string.substring(1), currentValue);
    }
}
