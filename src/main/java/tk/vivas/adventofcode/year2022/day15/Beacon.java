package tk.vivas.adventofcode.year2022.day15;

record Beacon(int x, int y) implements Comparable<Beacon> {
    @Override
    public int compareTo(Beacon other) {
        return y != other.y ? y - other.y : x - other.x;
    }
}
