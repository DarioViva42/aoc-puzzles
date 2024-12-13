package tk.vivas.adventofcode.year2024.day09;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class DiskFragmenterTest {

    @Test
    void filesystemChecksum() {
        String input = AocUtils.readPuzzleInput();

        DiskFragmenter diskFragmenter = new DiskFragmenter(input);

        assertThat(diskFragmenter.filesystemChecksum()).isEqualTo(1928);
    }
}