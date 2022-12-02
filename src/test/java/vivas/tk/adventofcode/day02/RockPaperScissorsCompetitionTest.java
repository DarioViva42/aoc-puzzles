package vivas.tk.adventofcode.day02;

import junit.framework.TestCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class RockPaperScissorsCompetitionTest extends TestCase {

    public void testCalculateScore() {
        List<String> input = readFile("/02.txt");
        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);
        assertThat(rockPaperScissorsCompetition.calculateScore()).isEqualTo(15);
    }

    public void testCalculateCorrectScore() {
        List<String> input = readFile("/02.txt");
        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);
        assertThat(rockPaperScissorsCompetition.calculateCorrectScore()).isEqualTo(12);
    }
}