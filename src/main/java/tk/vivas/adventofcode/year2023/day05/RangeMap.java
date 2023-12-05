package tk.vivas.adventofcode.year2023.day05;

import java.util.List;

class RangeMap {

    private final List<RangeMapEntry> mapEntries;

    public RangeMap(String raw) {
        String[] split = raw.split("\n", 2);

        mapEntries = split[1].lines()
                .map(RangeMapEntry::of)
                .toList();
    }

    long get(long source) {
        for (RangeMapEntry entry : mapEntries) {
            if (entry.canMapSource(source)) {
                return entry.get(source);
            }
        }
        return source;
    }
}
