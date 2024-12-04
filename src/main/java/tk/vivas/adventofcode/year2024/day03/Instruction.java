package tk.vivas.adventofcode.year2024.day03;

import java.util.regex.MatchResult;

interface Instruction {
    long run();

    static Instruction create(MatchResult matchResult) {
        return switch (matchResult.group()) {
            case "do()" -> DoInstruction.instance();
            case "don't()" -> DontInstruction.instance();
            default -> createMulInstruction(matchResult); // mul
        };
    }

    static MulInstruction createMulInstruction(MatchResult matchResult) {
        long a = Long.parseLong(matchResult.group("a"));
        long b = Long.parseLong(matchResult.group("b"));

        return new MulInstruction(a, b);
    }
}
