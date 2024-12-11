package tk.vivas.adventofcode.year2024.day08;

import org.junit.jupiter.api.Test;
import tk.vivas.Position;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleAntinodeFinderTest {

    @Test
    void findAntinodes() {
        List<Position> antennas = List.of(
                new Position(3, 3),
                new Position(5, 4),
                new Position(4, 6));

        SimpleAntinodeFinder simpleAntinodeFinder = new SimpleAntinodeFinder(antennas, 10, 10);

        assertThat(simpleAntinodeFinder.findAntinodes()).containsExactlyInAnyOrder(
                new Position(2, 0),
                new Position(1, 2),
                new Position(6, 2),
                new Position(7, 5),
                new Position(3, 8),
                new Position(5, 9));
    }
}