package tk.vivas.adventofcode.year2022.day08;

record Tree(byte x, byte y, byte height) implements Comparable<Tree> {
    @Override
    public int compareTo(Tree other) {
        return y != other.y ? y - other.y : x - other.x;
    }
}
