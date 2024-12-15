package tk.vivas.adventofcode.year2024.day13;

record Button(int moveX, int moveY) {
    static Button of(String line) {
        String[] split = line.split("[+,]");
        int x = Integer.parseInt(split[1]);
        int y = Integer.parseInt(split[3]);
        return new Button(x, y);
    }
}
