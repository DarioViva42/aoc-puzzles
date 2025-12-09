package tk.vivas.adventofcode.year2025.day08;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DecorationProject {

    private static final Consumer<Object> NOOP = whatever -> {};

    private final List<JunctionBox> junctionBoxes;

    DecorationProject(String input) {
        junctionBoxes = input.lines()
                .map(JunctionBox::new)
                .toList();
    }

    long connectJunctionBoxes(int numberOfConnections) {
        IntStream.range(0, junctionBoxes.size() - 1)
                .mapToObj(i -> {
                    JunctionBox a = junctionBoxes.get(i);
                    return IntStream.range(i + 1, junctionBoxes.size())
                            .mapToObj(junctionBoxes::get)
                            .map(b -> new JunctionEdge(a, b));
                })
                .flatMap(Function.identity())
                .sorted(Comparator.comparing(JunctionEdge::distance))
                .filter(JunctionEdge::connect)
                .limit(numberOfConnections)
                .forEach(NOOP);

        return junctionBoxes.stream()
                .map(JunctionBox::getConnections)
                .distinct()
                .map(List::size)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }

}
