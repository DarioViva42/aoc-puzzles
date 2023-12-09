package tk.vivas.adventofcode.year2023.day08;

import java.util.List;

class InstructionGenerator {

    private int index;
    private final List<Instruction> instructionList;

    InstructionGenerator(String s) {
        instructionList = s.chars()
                .mapToObj(c -> (char) c)
                .map(Instruction::from)
                .toList();

        index = 0;
    }

    Instruction next() {
        Instruction nextInstruction = instructionList.get(index);
        index++;
        if (index == instructionList.size()) {
            index = 0;
        }
        return nextInstruction;
    }
}
