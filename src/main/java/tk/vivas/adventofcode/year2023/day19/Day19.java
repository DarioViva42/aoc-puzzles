package tk.vivas.adventofcode.year2023.day19;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day19 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        MachinePartsOrganizer organizer = new MachinePartsOrganizer(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = organizer.acceptedPropertiesSum();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
