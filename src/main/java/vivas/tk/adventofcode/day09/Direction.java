package vivas.tk.adventofcode.day09;

enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction parse(String token) {
        return switch (token) {
            case "L" -> LEFT;
            case "R" -> RIGHT;
            case "U" -> UP;
            case "D" -> DOWN;
            default -> throw new IllegalStateException("Unexpected value: " + token);
        };
    }
}
