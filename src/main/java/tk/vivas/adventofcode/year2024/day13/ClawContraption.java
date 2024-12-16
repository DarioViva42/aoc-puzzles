package tk.vivas.adventofcode.year2024.day13;

import java.util.Arrays;
import java.util.List;

class ClawContraption {

    private final List<ClawConfiguration> configurations;

    ClawContraption(String input) {
        configurations = Arrays.stream(input.split("\\n{2}"))
                .map(ClawConfiguration::of)
                .toList();
    }

    long fewestTokens() {
        return configurations.stream()
                .mapToInt(ClawConfiguration::simulateTokensToWin)
                .sum();
    }

    long fewestTokensWithConversionFix() {
        return configurations.stream()
                .map(ClawConfiguration::fixConversion)
                .mapToLong(ClawConfiguration::calculateTokensToWin)
                .sum();
    }
}
