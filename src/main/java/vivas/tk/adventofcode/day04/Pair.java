package vivas.tk.adventofcode.day04;

class Pair {

    private final int a;
    private final int b;

    private final int x;
    private final int y;

    public Pair(String input) {
        String[] temp = input.split(",");
        String[] firstSection = temp[0].split("-");
        String[] secondSection = temp[1].split("-");

        a = Integer.parseInt(firstSection[0]);
        b = Integer.parseInt(firstSection[1]);

        x = Integer.parseInt(secondSection[0]);
        y = Integer.parseInt(secondSection[1]);
    }

    public boolean hasFullOverlap() {
        return (a >= x && b <= y) || (x >= a && y <= b);
    }

    public boolean hasPartialOverlap() {
        return (b >= x && b <= y) || (y >= a && y <= b);
    }
}
