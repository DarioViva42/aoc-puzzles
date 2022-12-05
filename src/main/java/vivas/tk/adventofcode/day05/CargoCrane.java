package vivas.tk.adventofcode.day05;

import java.util.List;
import java.util.Stack;

public abstract class CargoCrane {

    protected List<Operation> operations;
    protected Stack<Character>[] stacks;

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void setStacks(Stack<Character>[] stacks) {
        this.stacks = stacks;
    }

    public void operateCrane() {
        operations.forEach(this::applyOperation);
    }
    protected abstract void applyOperation(Operation operation);
}
