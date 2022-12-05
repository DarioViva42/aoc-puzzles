package vivas.tk.adventofcode.day05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CraneOperation {
    private Stack<Character>[] stacks;

    private final Stack<Character>[] backupStacks;
    private final List<Operation> operations;
    public CraneOperation(List<String> input) {
        int splitPosition = input.indexOf("");
        String indicatorLine = input
                .get(splitPosition - 1)
                .stripTrailing();
        int temp = indicatorLine.lastIndexOf(" ");
        int numberOfStacks = Integer.parseInt(indicatorLine.substring(temp + 1));

        this.stacks = new Stack[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            this.stacks[i] = new Stack<>();
        }
        String format = "%-" + (4 * numberOfStacks - 1) + "s";

        List<String> stackInput = input.subList(0, splitPosition - 1);
        Collections.reverse(stackInput);

        stackInput.stream()
                .map(line -> String.format(format, line))
                .forEach(this::fillStackLevel);

        this.backupStacks = new Stack[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            backupStacks[i] = (Stack<Character>) stacks[i].clone();
        }

        this.operations = input
                .subList(splitPosition + 1, input.size()).stream()
                .map(Operation::new)
                .toList();
    }

    public void resetStacks() {
        stacks = backupStacks;
    }

    public String operateCargoCrane(CargoCrane cargoCrane) {
        cargoCrane.setOperations(operations);
        cargoCrane.setStacks(stacks);

        cargoCrane.operateCrane();
        return readCrates();
    }

    private String readCrates() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(stacks)
                .map(Stack::peek)
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private void fillStackLevel(String line) {
        for (int i = 0; i < stacks.length; i++) {
            char crate = line.charAt(4 * i + 1);
            if (crate != ' ') {
                stacks[i].push(crate);
            }
        }
    }
}
