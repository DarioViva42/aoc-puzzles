package vivas.tk.adventofcode.day05;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RearrangementProcedure {
    private Stack<Character>[] stacks;

    private final Stack<Character>[] backupStacks;
    private final List<Step> steps;

    public RearrangementProcedure(List<String> input) {
        int splitPosition = input.indexOf("");

        this.backupStacks = setupStacks(input.subList(0, splitPosition));

        this.steps = input
                .subList(splitPosition + 1, input.size()).stream()
                .map(Step::new)
                .toList();
    }

    private Stack<Character>[] setupStacks(List<String> stackInput) {
        int numberOfStacks = getNumberOfStacks(stackInput);

        createStacks(numberOfStacks);
        String format = "%-" + (4 * numberOfStacks - 1) + "s";

        Collections.reverse(stackInput);
        stackInput.stream()
                .skip(1)
                .map(line -> String.format(format, line))
                .forEach(this::fillStackLevel);

        return createBackup();
    }

    private void createStacks(int numberOfStacks) {
        this.stacks = new Stack[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            this.stacks[i] = new Stack<>();
        }
    }

    private Stack<Character>[] createBackup() {
        Stack<Character>[] backup = new Stack[stacks.length];
        for (int i = 0; i < stacks.length; i++) {
            backup[i] = (Stack<Character>) stacks[i].clone();
        }
        return backup;
    }

    private int getNumberOfStacks(List<String> stackInput) {
        String indicatorLine = stackInput
                .get(stackInput.size() - 1)
                .stripTrailing();
        int temp = indicatorLine.lastIndexOf(" ");
        return Integer.parseInt(indicatorLine.substring(temp + 1));
    }

    public void resetStacks() {
        stacks = backupStacks;
    }

    public String operateCargoCrane(CargoCrane cargoCrane) {
        cargoCrane.setSteps(steps);
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
