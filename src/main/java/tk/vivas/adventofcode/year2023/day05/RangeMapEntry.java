package tk.vivas.adventofcode.year2023.day05;

import java.util.Arrays;

final class RangeMapEntry {
    private final long destinationRangeStart;
    private final long sourceRangeStart;
    private final long rangeLength;

    private RangeMapEntry(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    static RangeMapEntry of(String raw) {
        String[] split = raw.split(" ");
        Long[] longs = Arrays.stream(split)
                .map(Long::parseLong)
                .toArray(Long[]::new);
        return new RangeMapEntry(longs[0], longs[1], longs[2]);
    }

    boolean canMapSource(long source) {
        return sourceRangeStart <= source && source < sourceRangeStart + rangeLength;
    }

    long get(long source) {
        return destinationRangeStart - sourceRangeStart + source;
    }
}
