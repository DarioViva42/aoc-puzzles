package tk.vivas.adventofcode.year2023.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tk.vivas.adventofcode.year2023.day05.RangeMapFlatteningUtils.flatten;

class IslandIslandAlmanac {

    List<Long> seeds;
    List<RangeMap> mapList;

    IslandIslandAlmanac(String input) {
        String[] split = input.split("\n\n", 2);
        String[] rawSeeds = split[0].substring(7).split(" ");
        seeds = Arrays.stream(rawSeeds)
                .map(Long::parseLong)
                .toList();

        String[] rawMaps = split[1].split("\n\n");
        mapList = Arrays.stream(rawMaps)
                .map(RangeMap::of)
                .toList();
    }

    long findClosestLocation() {
        return seeds.stream()
                .mapToLong(this::applyMaps)
                .min().orElseThrow();
    }

    private long applyMaps(long value) {
        return applyMaps(value, mapList);
    }

    private long applyMaps(long value, List<RangeMap> mapList) {
        for (RangeMap rangeMap : mapList) {
            value = rangeMap.get(value);
        }
        return value;
    }

    long findRealClosestLocation() {
        RangeMap flattenedMap = getInitialMap();

        for (RangeMap rangeMap : mapList) {
            flattenedMap = flatten(flattenedMap, rangeMap);
        }

        return flattenedMap.getMapEntries().stream()
                .mapToLong(RangeMapEntry::getDestinationRangeStart)
                .min().orElseThrow();
    }



    private RangeMap getInitialMap() {
        List<RangeMapEntry> initialEntries = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i += 2) {
            Long rangeStart = seeds.get(i);
            Long length = seeds.get(i + 1);
            initialEntries.add(new RangeMapEntry(rangeStart, rangeStart, length));
        }
        return new RangeMap(initialEntries);
    }
}
