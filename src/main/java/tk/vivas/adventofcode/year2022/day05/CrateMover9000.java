package tk.vivas.adventofcode.year2022.day05;

class CrateMover9000 extends CargoCrane {

    @Override
    public void applyOperation(Step step) {
        for (int i = 0; i < step.getAmount(); i++) {
            char crate = stacks[step.getFrom()].pop();
            stacks[step.getTo()].push(crate);
        }
    }
}
