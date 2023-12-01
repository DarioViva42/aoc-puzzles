package tk.vivas.adventofcode.day10;

import java.util.List;

class VideoSystem {

    List<Instruction> instructions;
    public VideoSystem(String input) {
        instructions = input.lines()
                .map(Instruction::parse)
                .toList();
    }

    public int calculateSignalStrength() {
        CPU cpu = new CPU();
        return instructions.stream()
                .mapToInt(instruction -> instruction.runOnCpu(cpu))
                .sum();
    }

    public String renderAsciiArt() {
        CPU cpu = new CPU();
        CRT crt = new CRT();
        for (Instruction instruction : instructions) {
            instruction.draw(crt, cpu);
        }
        return crt.render();
    }
}
