package vivas.tk.adventofcode.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

class OverlapFinderTest {

    @Test
    void countFullOverlaps() {
        List<String> input = readFile("/04.txt");

        OverlapFinder overlapFinder = new OverlapFinder(input);
        assertThat(overlapFinder.countFullOverlaps()).isEqualTo(2);
    }

    @Test
    void countPartialOverlaps() {
        List<String> input = readFile("/04.txt");

        OverlapFinder overlapFinder = new OverlapFinder(input);
        assertThat(overlapFinder.countPartialOverlaps()).isEqualTo(4);
    }
}