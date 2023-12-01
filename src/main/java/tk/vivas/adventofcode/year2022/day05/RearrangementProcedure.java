package tk.vivas.adventofcode.year2022.day05;

import java.util.*;
import java.util.stream.Stream;

class RearrangementProcedure {
    private CharacterStack[] stacks;

    private final CharacterStack[] backupStacks;
    private final List<Step> steps;

    public RearrangementProcedure(String input) {
        String[] split = input.split("(\\n|\\r\\n){2}", 2);

        this.backupStacks = setupStacks(split[0].lines().toList());

        this.steps = split[1].lines()
                .map(Step::new)
                .toList();
    }

    private CharacterStack[] setupStacks(List<String> stackInput) {
        int numberOfStacks = getNumberOfStacks(stackInput);

        createStacks(numberOfStacks);
        String format = "%-" + (4 * numberOfStacks - 1) + "s";

        List<String> reversedStackInput = new ArrayList<>(stackInput);
        Collections.reverse(reversedStackInput);
        reversedStackInput.stream()
                .skip(1)
                .map(line -> String.format(format, line))
                .forEach(this::fillStackLevel);

        return createBackup();
    }

    private void createStacks(int numberOfStacks) {
        this.stacks = Stream
                .generate(CharacterStack::new)
                .limit(numberOfStacks)
                .toArray(CharacterStack[]::new);
    }

    private CharacterStack[] createBackup() {
        return Arrays.stream(stacks)
                .map(CharacterStack::clone)
                .map(CharacterStack.class::cast)
                .toArray(CharacterStack[]::new);
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
                .map(CharacterStack::peek)
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
