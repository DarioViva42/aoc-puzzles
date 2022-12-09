package vivas.tk.adventofcode.day08;

public record Tree(Byte x, Byte y, Byte height) implements Comparable<Tree> {
    @Override
    public int compareTo(Tree other) {
        return y - other.y != 0 ? y - other.y : x - other.x;
    }
}
