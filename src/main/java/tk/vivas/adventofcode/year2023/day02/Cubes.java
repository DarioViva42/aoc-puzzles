package tk.vivas.adventofcode.year2023.day02;

public class Cubes {

    private final int amount;
    private final Color color;

    Cubes(String raw) {
        String[] split = raw.split(" ");
        amount = Integer.parseInt(split[0]);
        color = Color.valueOf(split[1]);
    }

    public int getAmount() {
        return amount;
    }

    public Color getColor() {
        return color;
    }
}
