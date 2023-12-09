package tk.vivas.adventofcode.year2023.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Sequence {

    private final List<Long> numbers;

    Sequence(List<Long> numbers) {
        this.numbers = numbers;
    }

    static Sequence of(String raw) {
        List<Long> numbers = Arrays.stream(raw.split(" "))
                .map(Long::parseLong)
                .toList();
        return new Sequence(numbers);
    }

    long findExtrapolatedValue() {
        if (containsOnlyZero()) {
            return 0;
        }
        long extrapolatedDifference = findDifferenceSequence().findExtrapolatedValue();
        return numbers.getLast() + extrapolatedDifference;
    }

    private Sequence findDifferenceSequence() {
        List<Long> differences = new ArrayList<>();
        for (int i = 0; i < numbers.size() - 1; i++) {
            long difference = numbers.get(i + 1) - numbers.get(i);
            differences.add(difference);
        }
        return new Sequence(differences);
    }

    private boolean containsOnlyZero() {
        for (Long number : numbers) {
            if (number != 0L) {
                return false;
            }
        }
        return true;
    }
}
