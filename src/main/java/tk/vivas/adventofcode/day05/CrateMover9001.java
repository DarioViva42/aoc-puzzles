package tk.vivas.adventofcode.day05;

import java.util.ArrayDeque;
import java.util.Deque;

class CrateMover9001 extends CargoCrane {

    private final Deque<Character> temporaryStack = new ArrayDeque<>();

    @Override
    protected void applyOperation(Step step) {
        int amount = step.getAmount();
        for (int i = 0; i < amount; i++) {
            Character crate = stacks[step.getFrom()].pop();
            temporaryStack.push(crate);
        }
        for (int i = 0; i < amount; i++) {
            Character crate = temporaryStack.pop();
            stacks[step.getTo()].push(crate);
        }
    }
}
