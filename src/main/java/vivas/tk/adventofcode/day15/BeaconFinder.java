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
        List<Range> rangeList = rangesOnLine(lineNumber);
        removeOverlaps(rangeList);

        int inSight = rangeList.stream()
                .mapToInt(Range::getSize)
                .sum();
        int beaconsOnLine = (int) beaconSet.stream()
                .filter(beacon -> beacon.y() == lineNumber)
                .count();
        return inSight - beaconsOnLine;
    }

    public long findTuningFrequency(int max) {
        int secretX = 0;
        int secretY = 0;
        Range completeRange = new Range(0, max);
        for (int y = 0; y <= max; y++) {
            int xPosition = findGapInLine(y, completeRange);
            if (xPosition != -1) {
                secretX = xPosition;
                secretY = y;
                break;
            }
        }
        return secretX * 4_000_000L + secretY;
    }

    private int findGapInLine(int lineNumber, Range completeRange) {
        List<Range> rangeList = rangesOnLine(lineNumber);
        removeOverlaps(rangeList);
        return completeRange.findGap(rangeList);
    }

    @SuppressWarnings("java:S6204")
    private List<Range> rangesOnLine(int lineNumber) {
        return sensorList.stream()
                .map(sensor -> sensor.rangeOnLine(lineNumber))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void removeOverlaps(List<Range> rangeList) {
        boolean changed;
        do {
            changed = removeOverlapsOneIteration(rangeList);
        } while (changed);
    }

    private boolean removeOverlapsOneIteration(List<Range> rangeList) {
        for (int i = 0; i < rangeList.size(); i++) {
            for (int j = 0; j < i; j++) {
                Range rangeA = rangeList.get(i);
                Range rangeB = rangeList.get(j);
                if (!rangeA.isDisjoint(rangeB)) {
                    rangeList.add(rangeA.combine(rangeB));
                    rangeList.remove(rangeA);
                    rangeList.remove(rangeB);
                    return true;
                }
            }
        }
        return false;
    }
}
