package tk.vivas.adventofcode.day10;

final class NoOp implements Instruction {
    private static NoOp singleton;

    public static Instruction getInstance() {
        if (singleton == null) {
            singleton = new NoOp();
        }
        return singleton;
    }

    @Override
    public int runOnCpu(CPU cpu) {
        int signalStrength = cpu.signalStrength();
        cpu.tick();
        return signalStrength;
    }

    @Override
    public void draw(CRT crt, CPU cpu) {
        cpu.drawTo(crt);
        cpu.tick();
    }
}
