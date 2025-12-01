package tk.vivas.adventofcode.year2025.day01;

enum RotationDirection {
    ROTATE_LEFT,
    ROTATE_RIGHT;

    public static final int DIAL_SIZE = 100;

    static RotationDirection of(char character) {
        return switch (character) {
            case 'R' -> ROTATE_RIGHT;
            case 'L' -> ROTATE_LEFT;
            default -> throw new IllegalArgumentException();
        };
    }

    public int apply(int currentPosition, int amount) {
        return switch (this) {
            case ROTATE_LEFT -> (currentPosition - amount + DIAL_SIZE) % DIAL_SIZE;
            case ROTATE_RIGHT -> (currentPosition + amount) % DIAL_SIZE;
        };
    }
}
