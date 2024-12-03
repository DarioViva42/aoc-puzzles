package tk.vivas.adventofcode.year2024.day03;

class DoInstruction implements Instruction {

    private static final DoInstruction instance = new DoInstruction();
    private boolean active;

    private DoInstruction() {
        active = true;
    }

    static DoInstruction instance() {
        return instance;
    }

    boolean isActive() {
        return active;
    }

    void enable() {
        active = true;
    }

    void disable() {
        active = false;
    }

    @Override
    public long run() {
        enable();
        return 0;
    }
}
