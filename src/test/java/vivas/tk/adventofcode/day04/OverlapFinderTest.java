package vivas.tk.adventofcode.day04;

import junit.framework.TestCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class OverlapFinderTest extends TestCase {

    public void testCountFullOverlaps() {
        List<String> input = readFile("/04.txt");

        OverlapFinder overlapFinder = new OverlapFinder(input);
        assertThat(overlapFinder.countFullOverlaps()).isEqualTo(2);
    }

    public void testCountPartialOverlaps() {
        List<String> input = readFile("/04.txt");

        OverlapFinder overlapFinder = new OverlapFinder(input);
        assertThat(overlapFinder.countPartialOverlaps()).isEqualTo(4);
    }
}