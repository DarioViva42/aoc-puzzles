package tk.vivas.adventofcode.year2023.day02;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class SnowIslandGamesTest {

    @Test
    void sumOfPossibleGameIds() {
        String input = AocUtils.readPuzzleInput();

        SnowIslandGames games = new SnowIslandGames(input);

        int sum = games.sumOfPossibleGameIds();

        assertThat(sum).isEqualTo(8);
    }
}