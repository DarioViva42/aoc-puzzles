package tk.vivas.adventofcode.year2024.day09;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class FileArrangerTest {

    @Test
    void filesystemChecksumWithFragmenting() {
        String input = AocUtils.readPuzzleInput();

        FileArranger fileArranger = new FileArranger(input);

        assertThat(fileArranger.filesystemChecksumWithFragmenting()).isEqualTo(1928);
    }

    @Test
    void filesystemChecksumWithoutFragmenting() {
        String input = AocUtils.readPuzzleInput();

        FileArranger fileArranger = new FileArranger(input);

        assertThat(fileArranger.filesystemChecksumWithoutFragmenting()).isEqualTo(2858);
    }
}