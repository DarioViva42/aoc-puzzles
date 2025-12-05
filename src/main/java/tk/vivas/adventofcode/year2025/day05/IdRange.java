package tk.vivas.adventofcode.year2025.day05;

class IdRange {

    private final long start;
    private final long end;

    IdRange(String input) {
        String[] split = input.split("-");
        start = Long.parseLong(split[0]);
        end = Long.parseLong(split[1]);
    }

    boolean contains(long id) {
        return id >= start && id <= end;
    }
}
