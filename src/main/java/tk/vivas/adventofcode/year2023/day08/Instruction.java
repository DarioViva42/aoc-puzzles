package tk.vivas.adventofcode.year2023.day08;

enum Instruction {
    LEFT, RIGHT;

    public static Instruction from(Character character) {
        return character == 'L' ? LEFT : RIGHT;
    }
}
