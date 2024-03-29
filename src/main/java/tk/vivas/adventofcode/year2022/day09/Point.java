package tk.vivas.adventofcode.year2022.day09;

record Point(int x, int y) implements Comparable<Point> {
    @Override
    public int compareTo(Point other) {
        return y != other.y ? y - other.y : x - other.x;
    }
}
