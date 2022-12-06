package vivas.tk.adventofcode.day05;

import java.util.List;
import java.util.Stack;

public abstract class CargoCrane {

    protected List<Step> steps;
    protected Stack<Character>[] stacks;

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void setStacks(Stack<Character>[] stacks) {
        this.stacks = stacks;
    }

    public void operateCrane() {
        steps.forEach(this::applyOperation);
    }
    protected abstract void applyOperation(Step step);
}
