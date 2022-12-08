package vivas.tk.adventofcode.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class RockPaperScissorsCompetitionTest {

    @Test
    void calculateScore() {
        List<String> input = readPuzzleInput();
        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);
        assertThat(rockPaperScissorsCompetition.calculateScore()).isEqualTo(15);
    }

    @Test
    void calculateCorrectScore() {
        List<String> input = readPuzzleInput();
        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);
        assertThat(rockPaperScissorsCompetition.calculateCorrectScore()).isEqualTo(12);
    }
}