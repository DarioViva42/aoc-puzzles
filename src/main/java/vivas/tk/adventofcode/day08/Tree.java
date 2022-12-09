package vivas.tk.adventofcode.day08;

public record Tree(byte x, byte y, byte height) implements Comparable<Tree> {
    @Override
    public int compareTo(Tree other) {
        return y != other.y ? y - other.y : x - other.x;
    }
}
