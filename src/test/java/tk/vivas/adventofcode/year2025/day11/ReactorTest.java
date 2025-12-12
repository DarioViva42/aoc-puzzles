package tk.vivas.adventofcode.year2025.day11;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReactorTest {

    @Test
    void numberOfPaths() {
        String input = AocUtils.readPuzzleInput();

        Reactor reactor = new Reactor(input);

        assertThat(reactor.numberOfPaths()).isEqualTo(5);
    }
}
