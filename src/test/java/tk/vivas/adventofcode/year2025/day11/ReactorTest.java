package tk.vivas.adventofcode.year2025.day11;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class ReactorTest {

    @Test
    void numberOfPaths() {
        String input = AocUtils.readPuzzleInput(1);

        Reactor reactor = new Reactor(input);

        assertThat(reactor.numberOfPaths()).isEqualTo(5);
    }

    @Test
    void numberOfValidPaths() {
        String input = AocUtils.readPuzzleInput(2);

        Reactor reactor = new Reactor(input);

        assertThat(reactor.numberOfValidPaths()).isEqualTo(2);
    }
}
