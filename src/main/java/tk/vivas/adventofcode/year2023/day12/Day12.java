package tk.vivas.adventofcode.year2023.day12;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day12 {
    public static void main(String[] args){
        String input = readPuzzleInput();

        Instant start = Instant.now();

        HotSpringsRecords records = new HotSpringsRecords(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = records.countArrangements();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = records.countUnfoldedArrangements();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
