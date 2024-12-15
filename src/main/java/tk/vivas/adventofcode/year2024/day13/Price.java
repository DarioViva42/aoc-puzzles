package tk.vivas.adventofcode.year2024.day13;

record Price(int xPos, int yPos) {
    static Price of(String line) {
        String[] split = line.split("[=,]");
        int x = Integer.parseInt(split[1]);
        int y = Integer.parseInt(split[3]);
        return new Price(x, y);
    }
}
