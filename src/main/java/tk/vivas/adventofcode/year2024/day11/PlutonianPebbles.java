package tk.vivas.adventofcode.year2024.day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class PlutonianPebbles {

    private final List<Long> pebbles;

    PlutonianPebbles(String input) {
        input = input.stripTrailing();
        pebbles = Arrays.stream(input.split(" "))
                .map(Long::parseLong)
                .toList();
    }

    int countStonesAfterShortTime() {
        return countStones(25);
    }

    int countStonesAfterLongTime() {
        return countStones(75);
    }

    int countStones(int iterations) {
        List<Long> currentPebbles = pebbles;
        for (int i = 0; i < iterations; i++) {
            currentPebbles = evolve(currentPebbles);
        }
        return currentPebbles.size();
    }

    private List<Long> evolve(List<Long> currentPebbles) {
        return currentPebbles.stream()
                .flatMap(this::evolve)
                .toList();
    }

    private Stream<Long> evolve(long pebbleValue) {
        if (pebbleValue == 0) {
            return Stream.of(1L);
        }
        if (hasEvenDigits(pebbleValue)) {
            return split(pebbleValue);
        }
        return Stream.of(2024 * pebbleValue);
    }

    private Stream<Long> split(long pebbleValue) {
        int length = getValueLength(pebbleValue) / 2;
        long temp = (long) Math.pow(10, length);
        long left = pebbleValue / temp;
        long right = pebbleValue - left * temp;
        return Stream.of(left, right);
    }

    private static boolean hasEvenDigits(long pebbleValue) {
        return getValueLength(pebbleValue) % 2 == 0;
    }

    private static int getValueLength(long pebbleValue) {
        return (int) Math.floor(Math.log10(pebbleValue)) + 1;
    }
}
