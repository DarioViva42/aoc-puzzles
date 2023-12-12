package tk.vivas.adventofcode.year2023.day05;

import java.util.List;

class RangeMap {

    private final List<RangeMapEntry> mapEntries;

    RangeMap(List<RangeMapEntry> mapEntries) {
        this.mapEntries = mapEntries;
    }

    static RangeMap of(String raw) {
        String[] split = raw.split("\n", 2);

        List<RangeMapEntry> mapEntries = split[1].lines()
                .map(RangeMapEntry::of)
                .toList();

        return new RangeMap(mapEntries);
    }

    long get(long source) {
        for (RangeMapEntry entry : mapEntries) {
            if (entry.canMapSource(source)) {
                return entry.get(source);
            }
        }
        return source;
    }

    public List<RangeMapEntry> getMapEntries() {
        return mapEntries;
    }
}
