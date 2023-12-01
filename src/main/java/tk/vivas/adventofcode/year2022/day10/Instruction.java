package tk.vivas.adventofcode.year2022.day10;

sealed interface Instruction permits AddX, NoOp {

    int runOnCpu(CPU cpu);

    void draw(CRT crt, CPU cpu);

    static Instruction parse(String line) {
        if ("noop".equals(line)) {
            return NoOp.getInstance();
        }
        int x = Integer.parseInt(line.substring(5));
        return new AddX(x);
    }
}
