package tk.vivas.adventofcode.year2025.day07;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class TachyonManifoldTest {

    @Test
    void usedSplitters() {
        String input = AocUtils.readPuzzleInput();

        TachyonManifold manifold = new TachyonManifold(input);

        assertThat(manifold.usedSplitters()).isEqualTo(21);
    }

    @Test
    void timelines() {
        String input = AocUtils.readPuzzleInput();

        TachyonManifold manifold = new TachyonManifold(input);

        assertThat(manifold.timelines()).isEqualTo(40);
    }
}