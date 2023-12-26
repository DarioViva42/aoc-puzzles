package tk.vivas.adventofcode.year2023.day23;

record Point(int x, int y) {
    @Override
    public String toString() {
        return "[%02x,%02x]".formatted(x, y);
    }
}
