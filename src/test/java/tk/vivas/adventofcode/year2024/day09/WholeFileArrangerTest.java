package tk.vivas.adventofcode.year2024.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WholeFileArrangerTest {

    @Test
    void getArrangedMap() {
        List<DiskMapToken> sourceList = List.of(
                new DiskMapToken(0, 1, 3),
                new DiskMapToken(1, 1, 4),
                new DiskMapToken(2, 2, 6),
                new DiskMapToken(3, 5, 0));

        WholeFileArranger wholeFileArranger = new WholeFileArranger(sourceList);

        assertThat(wholeFileArranger.getArrangedMap()).containsExactly(
                new DiskMapToken(0, 1, 0),
                new DiskMapToken(2, 2, 0),
                new DiskMapToken(1, 1, 7),
                new DiskMapToken(3, 5, 6));
    }
}