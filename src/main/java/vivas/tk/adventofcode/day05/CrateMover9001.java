package vivas.tk.adventofcode.day05;

public class CrateMover9001 extends CargoCrane {
    @Override
    protected void applyOperation(Step step) {
        int amount = step.getAmount();
        Character[] crates = new Character[amount];
        for (int i = 0; i < amount; i++) {
            crates[i] = stacks[step.getFrom()].pop();
        }
        for (int i = 0; i < amount; i++) {
            stacks[step.getTo()].push(crates[amount - i - 1]);
        }
    }
}
