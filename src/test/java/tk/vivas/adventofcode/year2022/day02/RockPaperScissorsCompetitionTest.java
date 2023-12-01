package tk.vivas.adventofcode.year2022.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

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