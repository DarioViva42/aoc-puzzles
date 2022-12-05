package vivas.tk.adventofcode.day05;

public class CrateMover9000 extends CargoCrane {

    @Override
    public void applyOperation(Operation operation) {
        for (int i = 0; i < operation.getAmount(); i++) {
            char crate = stacks[operation.getFrom()].pop();
            stacks[operation.getTo()].push(crate);
        }
    }
}
