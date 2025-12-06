package tk.vivas.adventofcode.year2025.day06;

enum Operator {
    MULTIPLY,
    ADD;

    static Operator of(String input) {
        return switch (input) {
            case "*" -> MULTIPLY;
            case "+" -> ADD;
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };
    }
}
