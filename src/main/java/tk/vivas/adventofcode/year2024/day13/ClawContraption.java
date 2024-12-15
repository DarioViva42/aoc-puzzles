package tk.vivas.adventofcode.year2024.day13;

import java.util.Arrays;
import java.util.List;

class ClawContraption {

    private final List<ClawConfiguration> configurations;

    ClawContraption(String input) {
        configurations = Arrays.stream(input.split("\\n{2}"))
                .map(ClawConfiguration::new)
                .toList();
    }

    long fewestTokens() {
        return configurations.stream()
                .mapToInt(ClawConfiguration::minTokensToWin)
                .sum();
    }
}
