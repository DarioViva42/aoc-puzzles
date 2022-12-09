package vivas.tk.adventofcode.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class RockPaperScissorsCompetitionTest {

    @Test
    void calculateScore() {
        String input = readPuzzleInput();
        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);

        assertThat(rockPaperScissorsCompetition.calculateScore()).isEqualTo(15);
    }

    @Test
    void calculateCorrectScore() {
        String input = readPuzzleInput();
        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);

        assertThat(rockPaperScissorsCompetition.calculateCorrectScore()).isEqualTo(12);
    }
}