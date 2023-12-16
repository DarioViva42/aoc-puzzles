package tk.vivas.adventofcode.year2023.day15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class HASHMAP {

    private final AsciiStringHelperAlgorithm helperAlgorithm;
    private final List<Instruction> instructions;
    private final List<LensBox> boxList;

    HASHMAP(String input) {
        String[] instructionStrings = input.strip().split(",");

        helperAlgorithm = new AsciiStringHelperAlgorithm(instructionStrings);

        instructions = Arrays.stream(instructionStrings)
                .map(Instruction::createInstruction)
                .toList();

        boxList = IntStream.range(0, 256)
                .mapToObj(LensBox::new)
                .toList();
    }

    long prepare() {
        return helperAlgorithm.hashSum();
    }

    long runProcedure() {
        instructions.forEach(this::runInstruction);

        return boxList.stream()
                .mapToLong(LensBox::focusingPower)
                .sum();
    }

    private void runInstruction(Instruction instruction) {
        String label = instruction.label();
        int boxNumber = helperAlgorithm.hash(label);
        LensBox box = boxList.get(boxNumber);
        instruction.run(box);
    }
}
