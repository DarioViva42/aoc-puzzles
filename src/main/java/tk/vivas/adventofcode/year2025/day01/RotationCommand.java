package tk.vivas.adventofcode.year2025.day01;

record RotationCommand(RotationDirection direction, int amount) {
    RotationCommand(String input) {
        this(
                RotationDirection.of(input.charAt(0)),
                Integer.parseInt(input.substring(1))
        );
    }
}
