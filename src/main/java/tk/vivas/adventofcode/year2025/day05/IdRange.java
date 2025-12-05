package tk.vivas.adventofcode.year2025.day05;

class IdRange {

    private final long start;
    private final long end;

    IdRange(String input) {
        String[] split = input.split("-");
        start = Long.parseLong(split[0]);
        end = Long.parseLong(split[1]);
    }

    IdRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    boolean contains(long id) {
        return id >= start && id <= end;
    }

    long totalIds() {
        return end - start + 1;
    }

    boolean mergeable(IdRange other) {
        boolean larger = other.start <= this.start && other.end >= this.end;
        boolean smaller = other.start >= this.start && other.end <= this.end;
        boolean toTheLeft = other.end >= this.start && other.end <= this.end;
        boolean toTheRight = other.start >= this.start && other.start <= this.end;
        return larger || smaller || toTheLeft || toTheRight;
    }

    IdRange merge(IdRange other) {
        long mergedStart = Math.min(this.start, other.start);
        long mergedEnd = Math.max(this.end, other.end);

        return new IdRange(mergedStart, mergedEnd);
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}
