package tk.vivas.adventofcode.year2023.day08;

import tk.vivas.MathUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class NetworkDocument {

    private final InstructionGenerator generator;
    private final Node startNode;
    private final Stream<Node> ghostStartNodes;

    NetworkDocument(String input) {
        String[] split = input.split("\n\n");
        generator = new InstructionGenerator(split[0]);

        List<Node> nodeList = split[1].lines()
                .map(Node::new)
                .toList();

        Map<String, Node> nodeMap = nodeList.stream()
                .collect(Collectors.toMap(Node::getName, Function.identity()));

        nodeList.forEach(node -> node.init(nodeMap));

        startNode = nodeMap.get("AAA");

        ghostStartNodes = nodeMap.entrySet().stream()
                .filter(e -> e.getKey().endsWith("A"))
                .map(Map.Entry::getValue);
    }

    long requiredSteps() {
        generator.seed();
        return requiredSteps(startNode, Node::isEndNode);
    }

    private long requiredSteps(Node startNode, Predicate<Node> endNodePredicate) {
        long stepsNeeded = 0;
        generator.seed();
        Node currentNode = startNode;
        while (!endNodePredicate.test(currentNode)) {
            currentNode = currentNode.get(generator.next());
            stepsNeeded++;
        }
        return stepsNeeded;
    }

    public long requiredGhostSteps() {
        long[] stepList = ghostStartNodes
                .mapToLong(ghostStartNode -> requiredSteps(ghostStartNode, Node::isGhostEndNode))
                .toArray();
        return MathUtils.lcm(stepList);
    }
}
