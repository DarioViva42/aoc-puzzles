package vivas.tk.adventofcode.day05;

import java.util.List;

public abstract class CargoCrane {

    protected List<Step> steps;
    protected CharacterStack[] stacks;

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void setStacks(CharacterStack[] stacks) {
        this.stacks = stacks;
    }

    public void operateCrane() {
        steps.forEach(this::applyOperation);
    }
    protected abstract void applyOperation(Step step);
}
