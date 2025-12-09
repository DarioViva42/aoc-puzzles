package tk.vivas.adventofcode.year2025.day08;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

class DecorationProject {

    private final List<JunctionBox> junctionBoxes;
    private final List<JunctionEdge> junctionEdges;

    DecorationProject(String input) {
        junctionBoxes = input.lines()
                .map(JunctionBox::new)
                .toList();

        junctionEdges = IntStream.range(0, junctionBoxes.size() - 1)
                .mapToObj(i -> {
                    JunctionBox a = junctionBoxes.get(i);
                    return IntStream.range(i + 1, junctionBoxes.size())
                            .mapToObj(junctionBoxes::get)
                            .map(b -> new JunctionEdge(a, b));
                })
                .flatMap(Function.identity())
                .sorted(Comparator.comparing(JunctionEdge::squaredDistance))
                .toList();
    }

    long connectJunctionBoxes(int numberOfConnections) {
        junctionEdges.stream()
                .limit(numberOfConnections)
                .forEach(JunctionEdge::connect);

        return junctionBoxes.stream()
                .map(JunctionBox::getConnections)
                .distinct()
                .map(List::size)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }

    long connectAllJunctionBoxes() {
        return junctionEdges.stream()
                .dropWhile(not(this::isLastConnection))
                .findFirst().orElseThrow()
                .wallDistance();
    }

    private boolean isLastConnection(JunctionEdge junctionEdge) {
        return junctionEdge.connect() == junctionBoxes.size();
    }
}
