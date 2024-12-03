package tk.vivas.adventofcode.year2024.day03;

import java.util.List;
import java.util.regex.Pattern;

class CorruptedMemory {

    private static final Pattern PATTERN = Pattern
            .compile("(?<mul>mul)\\((?<a>\\d{1,3}),(?<b>\\d{1,3})\\)|(?<do>do)\\(\\)|(?<dont>don't)\\(\\)");

    private final List<Instruction> instructions;

    CorruptedMemory(String input) {
        instructions = PATTERN.matcher(input).results()
                .map(Instruction::create)
                .toList();
    }

    long runMulInstructions() {
        return instructions.stream()
                .filter(MulInstruction.class::isInstance)
                .mapToLong(Instruction::run)
                .sum();
    }

    long runAllInstructions() {
        DoInstruction.instance().enable();
        return instructions.stream()
                .mapToLong(Instruction::run)
                .sum();
    }
}
