package tk.vivas.adventofcode.year2024.day03;

import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

interface Instruction {
    long run();

    static Instruction create(MatchResult matchResult) {
        String instructionType = Stream.of("mul", "do")
                .map(matchResult::group)
                .filter(Objects::nonNull)
                .findAny().orElseThrow();

        return switch (instructionType) {
            case "mul" -> createMulInstruction(matchResult);
            case "do" -> DoInstruction.instance();
            case "don't" -> DontInstruction.instance();
            default -> throw new IllegalStateException("Unexpected value: " + instructionType);
        };
    }

    static MulInstruction createMulInstruction(MatchResult matchResult) {
        long a = Long.parseLong(matchResult.group("a"));
        long b = Long.parseLong(matchResult.group("b"));

        return new MulInstruction(a, b);
    }
}
