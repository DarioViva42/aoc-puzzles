package tk.vivas.adventofcode.year2023.day18;

record DigInstruction(DigDirection direction, long amount, String color) {

    static DigInstruction parse(String raw) {
        String[] split = raw.split(" ");
        DigDirection direction = DigDirection.from(split[0]);
        int amount = Integer.parseInt(split[1]);
        String color = split[2].substring(1, split[2].length() - 1);
        return new DigInstruction(direction, amount, color);
    }

    DigInstruction fromColor() {
        String amountPart = color.substring(1, color.length() - 1);
        long amountLong = Long.parseLong(amountPart, 16);
        char directionPart = color.charAt(color.length() - 1);
        DigDirection mappedDirection = DigDirection.fromColorChar(directionPart);
        return new DigInstruction(mappedDirection, amountLong, color);
    }
}
