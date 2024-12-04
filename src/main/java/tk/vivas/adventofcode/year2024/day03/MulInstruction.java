package tk.vivas.adventofcode.year2024.day03;

class MulInstruction implements Instruction {

    private final long a;
    private final long b;

    MulInstruction(long a, long b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public long run() {
        return DoInstruction.instance().isActive() ? a * b : 0;
    }
}
