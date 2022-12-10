package vivas.tk.adventofcode.day10;

final class NoOp implements Instruction {
    private static NoOp singleton;

    public static Instruction getInstance() {
        if (singleton == null) {
            singleton = new NoOp();
        }
        return singleton;
    }

    @Override
    public int runOnCpu(Cpu cpu) {
        return cpu.tick();
    }
}
