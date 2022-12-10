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

    @Test
    void drawTo() {
        String input = readPuzzleInput();
        VideoSystem videoSystem = new VideoSystem(input);

        String solution = """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....""";

        assertThat(videoSystem.renderAsciiArt()).isEqualTo(solution);
    }
}