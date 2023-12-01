package tk.vivas.adventofcode.day13;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class DistressSignalFinderTest {

    @Test
    void evaluateCorrectlyOrderedPairs() {
        String input = readPuzzleInput();
        DistressSignalFinder signalFinder = new DistressSignalFinder(input);

        assertThat(signalFinder.evaluateCorrectlyOrderedPairs()).isEqualTo(13);
    }

    @Test
    void findDecoderKey() {
        String input = readPuzzleInput();
        DistressSignalFinder signalFinder = new DistressSignalFinder(input);

        assertThat(signalFinder.findDecoderKey()).isEqualTo(140);
    }
}