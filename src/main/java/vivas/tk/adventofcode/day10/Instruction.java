package vivas.tk.adventofcode.day10;

sealed interface Instruction permits AddX, NoOp {

    int runOnCpu(Cpu cpu);
    static Instruction parse(String line) {
        if ("noop".equals(line)) {
            return NoOp.getInstance();
        }
        int x = Integer.parseInt(line.substring(5));
        return new AddX(x);
    }
}
