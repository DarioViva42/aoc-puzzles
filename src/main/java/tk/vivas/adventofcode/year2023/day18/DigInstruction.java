package tk.vivas.adventofcode.year2023.day18;

record DigInstruction(DigDirection direction, int amount, String color) {

    static DigInstruction parse(String raw) {
        String[] split = raw.split(" ");
        DigDirection direction = DigDirection.from(split[0]);
        int amount = Integer.parseInt(split[1]);
        String color = split[2].substring(1, split[2].length() - 1);
        return new DigInstruction(direction, amount, color);
    }

}
