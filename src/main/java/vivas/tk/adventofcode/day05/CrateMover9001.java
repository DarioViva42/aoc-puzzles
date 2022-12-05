package vivas.tk.adventofcode.day05;

public class CrateMover9001 extends CargoCrane {
    @Override
    protected void applyOperation(Operation operation) {
        int amount = operation.getAmount();
        Character[] crates = new Character[amount];
        for (int i = 0; i < amount; i++) {
            crates[i] = stacks[operation.getFrom()].pop();
        }
        for (int i = 0; i < amount; i++) {
            stacks[operation.getTo()].push(crates[amount - i - 1]);
        }
    }
}
