package tk.vivas.adventofcode.year2025.day06;

import java.util.List;
import java.util.stream.LongStream;

class MathProblem {
    private final Operator operator;
    private final List<Long> numbers;

    MathProblem(Operator operator, List<Long> numbers) {
        this.operator = operator;
        this.numbers = numbers;
    }

    long solve() {
        LongStream stream = numbers.stream()
                .mapToLong(Long::longValue);
        return switch (operator) {
            case ADD -> stream.sum();
            case MULTIPLY -> stream.reduce(1, (a, b) -> a * b);
        };
    }
}
