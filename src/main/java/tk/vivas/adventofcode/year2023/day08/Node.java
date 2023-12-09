package tk.vivas.adventofcode.year2023.day08;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Node {

    private static final Pattern NODE_PATTERN = Pattern.compile("(\\w{3}) = \\((\\w{3}), (\\w{3})\\)");
    private final String name;
    private final String leftName;
    private final boolean endNode;
    private final boolean ghostEndNode;
    private Node left;
    private final String rightName;
    private Node right;

    Node(String raw) {
        Matcher matcher = NODE_PATTERN.matcher(raw);
        if (!matcher.matches()) {
            throw new IllegalStateException("corrupted input");
        }
        name = matcher.group(1);
        leftName = matcher.group(2);
        rightName = matcher.group(3);

        endNode = "ZZZ".equals(name);
        ghostEndNode = name.endsWith("Z");
    }

    boolean isEndNode() {
        return endNode;
    }

    boolean isGhostEndNode() {
        return ghostEndNode;
    }

    void init(Map<String, Node> map) {
        this.left = map.get(leftName);
        this.right = map.get(rightName);
    }

    String getName() {
        return name;
    }

    Node get(Instruction instruction) {
        return switch (instruction) {
            case LEFT -> left;
            case RIGHT -> right;
        };
    }
}
