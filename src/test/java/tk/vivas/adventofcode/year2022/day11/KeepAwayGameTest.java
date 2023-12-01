package tk.vivas.adventofcode.year2022.day11;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class KeepAwayGameTest {

    @Test
    void play() {
        String input = readPuzzleInput();
        KeepAwayGame keepAwayGame = new KeepAwayGame(input);

        assertThat(keepAwayGame.play(20)).isEqualTo(10605);
    }

    @Test
    void playWithoutRelief() {
        String input = readPuzzleInput();
        KeepAwayGame keepAwayGame = new KeepAwayGame(input);

        assertThat(keepAwayGame.playWithoutRelief(10000)).isEqualTo(2713310158L);
    }
}