package vivas.tk.adventofcode.day10;

import java.util.List;

class VideoSystem {

    List<Instruction> instructions;
    public VideoSystem(String input) {
        instructions = input.lines()
                .map(Instruction::parse)
                .toList();
    }

    public int calculateSignalStrength() {
        Cpu cpu = new Cpu();
        return instructions.stream()
                .mapToInt(instruction -> instruction.runOnCpu(cpu))
                .sum();
    }
}
