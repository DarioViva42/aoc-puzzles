package tk.vivas.adventofcode.year2023.day06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class RaceDocument {

    private final List<RaceRecord> raceRecords;

    RaceDocument(String input) {
        List<String> lines = input.lines().toList();
        String[] timesRaw = lines.getFirst().split(": +");
        List<Integer> times = Arrays.stream(timesRaw[1].split(" +"))
                .map(Integer::parseInt)
                .toList();
        String[] distancesRaw = lines.getLast().split(": +");
        List<Integer> distances = Arrays.stream(distancesRaw[1].split(" +"))
                .map(Integer::parseInt)
                .toList();
        raceRecords = Stream.iterate(0, i -> i < times.size(), i -> i + 1)
                .map(i -> new RaceRecord(times.get(i), distances.get(i)))
                .toList();
    }

    public int marginOfError() {
        return raceRecords.stream()
                .mapToInt(RaceRecord::marginOfError)
                .reduce(1, (a, b) -> a * b);
    }
}
