package tk.vivas.adventofcode.year2024.day04;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class WordSearchTest {

    @Test
    void countXMAS() {
        String input = AocUtils.readPuzzleInput();

        WordSearch wordSearch = new WordSearch(input);

        assertThat(wordSearch.countXMAS()).isEqualTo(18);
    }
}