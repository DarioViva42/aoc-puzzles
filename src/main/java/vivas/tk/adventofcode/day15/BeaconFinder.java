package vivas.tk.adventofcode.day15;

import java.util.*;
import java.util.stream.Collectors;

class BeaconFinder {
    private final List<Sensor> sensorList;
    private final Set<Beacon> beaconSet;
    public BeaconFinder(String input) {
        sensorList = new ArrayList<>();
        beaconSet = new TreeSet<>();

        input.lines().forEach(line -> {
            String[] tokens = line.split("x=|, y=|:");
            int sxParsed = Integer.parseInt(tokens[1]);
            int syParsed = Integer.parseInt(tokens[2]);
            int bxParsed = Integer.parseInt(tokens[4]);
            int byParsed = Integer.parseInt(tokens[5]);
            int manhattanDistance = Math.abs(sxParsed - bxParsed) + Math.abs(syParsed - byParsed);
            sensorList.add(new Sensor(sxParsed, syParsed, manhattanDistance));
            beaconSet.add(new Beacon(bxParsed, byParsed));
        });
    }

    public int countEmptyPositionsOnLine(int lineNumber) {
        List<Range> rangeList = sensorList.stream()
                .map(sensor -> sensor.rangeOnLine(lineNumber))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        boolean changed;
        do {
            changed = false;
            outer:
            for (int i = 0; i < rangeList.size(); i++) {
                for (int j = 0; j < i; j++) {
                    Range rangeA = rangeList.get(i);
                    Range rangeB = rangeList.get(j);
                    if (!rangeA.isDisjoint(rangeB)) {
                        rangeList.add(rangeA.combine(rangeB));
                        rangeList.remove(rangeA);
                        rangeList.remove(rangeB);
                        changed = true;
                        break outer;
                    }
                }
            }
        } while (changed);

        int inSight = rangeList.stream()
                .mapToInt(Range::getSize)
                .sum();
        int beaconsOnLine = (int) beaconSet.stream()
                .filter(beacon -> beacon.y() == lineNumber)
                .count();
        return inSight - beaconsOnLine;
    }
}
