package tk.vivas.adventofcode.year2023.day13;

import java.util.Arrays;
import java.util.List;

class MirrorValley {

    private final List<PatternNote> patternNotes;

    MirrorValley(String input) {
        patternNotes = Arrays.stream(input.split("\n\n"))
                .map(PatternNote::new)
                .toList();
    }

    long summarizePatterns() {
        long columns = patternNotes.stream()
                .mapToLong(PatternNote::columnsToTheLeftOfReflection)
                .sum();
        long rows =  patternNotes.stream()
                .mapToLong(PatternNote::rowsAboveReflection)
                .sum();
        return columns + 100 * rows;
    }

    long summarizePatternsClearingSmudge() {
        long columns = patternNotes.stream()
                .mapToLong(PatternNote::columnsToTheLeftOfReflectionClearingSmudge)
                .sum();
        long rows =  patternNotes.stream()
                .mapToLong(PatternNote::rowsAboveReflectionClearingSmudge)
                .sum();
        return columns + 100 * rows;
    }
}
