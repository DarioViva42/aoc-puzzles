package tk.vivas.adventofcode.year2023.day05;

import java.util.Arrays;
import java.util.List;

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
                .map(RangeMap::new)
                .toList();
    }

    long findClosestLocation() {
        return seeds.stream()
                .mapToLong(this::applyMaps)
                .min().orElseThrow();
    }

    private long applyMaps(long value) {
        for (RangeMap rangeMap : mapList) {
            value = rangeMap.get(value);
        }
        return value;
    }
}
