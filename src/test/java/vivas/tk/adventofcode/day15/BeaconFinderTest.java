package vivas.tk.adventofcode.day15;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class BeaconFinderTest {

    @Test
    void countEmptyPositionsOnLine() {
        String input = readPuzzleInput();
        BeaconFinder beaconFinder = new BeaconFinder(input);

        assertThat(beaconFinder.countEmptyPositionsOnLine(10)).isEqualTo(26);
    }

    @Test
    void findTuningFrequency() {
        String input = readPuzzleInput();
        BeaconFinder beaconFinder = new BeaconFinder(input);

        assertThat(beaconFinder.findTuningFrequency(20)).isEqualTo(56000011);
    }
}