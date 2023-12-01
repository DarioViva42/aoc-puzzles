package tk.vivas.adventofcode.year2022.day04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class OverlapFinderTest {

    @Test
    void countFullOverlaps() {
        String input = readPuzzleInput();
        OverlapFinder overlapFinder = new OverlapFinder(input);

        assertThat(overlapFinder.countFullOverlaps()).isEqualTo(2);
    }

    @Test
    void countPartialOverlaps() {
        String input = readPuzzleInput();
        OverlapFinder overlapFinder = new OverlapFinder(input);

        assertThat(overlapFinder.countPartialOverlaps()).isEqualTo(4);
    }
}