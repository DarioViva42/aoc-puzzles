package vivas.tk.adventofcode.day09;

record Instruction(Direction direction, int amount) {
    public static Instruction parse(String string) {
        String[] tokens = string.split(" ", 2);
        Direction direction = Direction.parse(tokens[0]);
        int amount = Integer.parseInt(tokens[1]);
        return new Instruction(direction, amount);
    }
}
