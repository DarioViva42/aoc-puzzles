package tk.vivas.adventofcode.year2024.day01;

record LocationPair(int left, int right) {
    int distance() {
        return Math.abs(left - right);
    }
}
