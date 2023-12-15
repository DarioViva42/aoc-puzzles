package tk.vivas.adventofcode.year2023.day15;

sealed interface Instruction permits AddInstruction, RemoveInstruction {
    static Instruction createInstruction(String raw) {
        if (raw.endsWith("-")) {
            return new RemoveInstruction(raw.substring(0, raw.length() - 1));
        }
        String[] split = raw.split("=");
        return new AddInstruction(split[0], Integer.parseInt(split[1]));
    }

    String label();

    void run(LensBox box);
}
