package tk.vivas.adventofcode.year2024.day03;

class DontInstruction implements Instruction {

    private static final DontInstruction instance = new DontInstruction();

    private DontInstruction() {
    }

    static DontInstruction instance() {
        return instance;
    }

    @Override
    public long run() {
        DoInstruction.instance().disable();
        return 0;
    }
}
