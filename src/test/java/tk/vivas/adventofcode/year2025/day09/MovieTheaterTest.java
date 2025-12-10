package tk.vivas.adventofcode.year2025.day09;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTheaterTest {

    @Test
    void largestRectangle() {
        String input = AocUtils.readPuzzleInput();

        MovieTheater movieTheater = new MovieTheater(input);

        assertThat(movieTheater.largestRectangle()).isEqualTo(50);
    }

    @Test
    void largestValidRectangle() {
        String input = AocUtils.readPuzzleInput();

        MovieTheater movieTheater = new MovieTheater(input);

        assertThat(movieTheater.largestValidRectangle()).isEqualTo(24);
    }
}
