package vivas.tk.adventofcode.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class VideoSystemTest {

    @Test
    void calculateSignalStrength() {
        String input = readPuzzleInput();
        VideoSystem videoSystem = new VideoSystem(input);

        assertThat(videoSystem.calculateSignalStrength()).isEqualTo(13140);
    }
}