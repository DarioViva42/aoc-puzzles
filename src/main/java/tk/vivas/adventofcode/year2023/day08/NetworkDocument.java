package tk.vivas.adventofcode.year2023.day08;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class NetworkDocument {

    private final InstructionGenerator generator;
    private final Node startNode;
    private final Node endNode;

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
        endNode = nodeMap.get("ZZZ");
    }

    long requiredSteps() {
        long stepsNeeded = 0;
        Node currentNode = startNode;
        while (currentNode != endNode) {
            currentNode = currentNode.get(generator.next());
            stepsNeeded++;
            if (currentNode == endNode) {
                break;
            }
        }
        return stepsNeeded;
    }
}
