package vivas.tk.adventofcode.day08;

import java.util.*;

import static java.util.function.Predicate.not;

public class TreePlantation {

    private final List<List<Tree>> trees;
    private final int height;
    private final int width;
    private final Set<Tree> visibleTrees;

    public TreePlantation(List<String> input) {
        this.trees = new ArrayList<>();
        for (byte y = 0; y < input.size(); y++) {
            List<Tree> temp = new ArrayList<>();
            String line = input.get(y);
            for (byte x = 0; x < line.length(); x++) {
                byte treeHeight = (byte) (line.charAt(x) - 48);
                temp.add(new Tree(x, y, treeHeight));
            }
            trees.add(temp);
        }
        height = trees.size();
        width = trees.get(0).size();
        visibleTrees = new HashSet<>();
    }

    public List<List<Tree>> getTrees() {
        return trees;
    }

    public long countVisibleTrees() {
        if (visibleTrees.isEmpty()) {
            addVisibleFromLeft();
            addVisibleFromRight();
            addVisibleFromTop();
            addVisibleFromBottom();
        }

        return visibleTrees.size();
    }

    private void addVisibleFromLeft() {
        for (List<Tree> line : trees) {
            Byte visibleHeight = -1;
            for (Tree tree : line) {
                visibleHeight = addFilteredTree(visibleHeight, tree);
            }
        }
    }

    private void addVisibleFromRight() {
        List<ArrayList<Tree>> reversedTrees = trees.stream()
                .map(ArrayList::new)
                .peek(Collections::reverse)
                .toList();

        for (List<Tree> line : reversedTrees) {
            Byte visibleHeight = -1;
            for (Tree tree : line) {
                visibleHeight = addFilteredTree(visibleHeight, tree);
            }
        }
    }

    private void addVisibleFromTop() {
        for (int x = 0; x < trees.get(0).size(); x++) {
            Byte visibleHeight = -1;
            for (List<Tree> line : trees) {
                Tree tree = line.get(x);
                visibleHeight = addFilteredTree(visibleHeight, tree);
            }
        }
    }

    private void addVisibleFromBottom() {
        List<List<Tree>> reversedTrees = new ArrayList<>(trees);
        Collections.reverse(reversedTrees);

        for (int x = 0; x < reversedTrees.get(0).size(); x++) {
            Byte visibleHeight = -1;
            for (List<Tree> line : reversedTrees) {
                Tree tree = line.get(x);
                visibleHeight = addFilteredTree(visibleHeight, tree);
            }
        }
    }

    private Byte addFilteredTree(Byte visibleHeight, Tree tree) {
        Byte currentHeight = tree.height();
        if (currentHeight > visibleHeight) {
            visibleHeight = currentHeight;
            visibleTrees.add(tree);
        }
        return visibleHeight;
    }

    public int findOptimalScenicScore() {
        return trees.stream()
                .flatMap(Collection::stream)
                .filter(tree -> tree.height() > 0)
                .filter(not(this::isOnBorder))
                .mapToInt(this::getScenicScore)
                .max().orElse(0);
    }

    public boolean isOnBorder(Tree tree) {
        return tree.x() == 0 || tree.y() == 0 || tree.x() == width - 1 || tree.y() == height - 1;
    }

    public int getScenicScore(Tree tree) {
        return viewingDistanceRight(tree) *
                viewingDistanceLeft(tree) *
                viewingDistanceDown(tree) *
                viewingDistanceUp(tree);
    }

    private int viewingDistanceRight(Tree tree) {
        int count = 1;
        while (true) {
            if (tree.x() + count >= width) {
                return count - 1;
            }
            Tree lookedAtTree = trees.get(tree.y()).get(tree.x() + count);
            if (lookedAtTree.height() >= tree.height()) {
                return count;
            }
            count++;
        }
    }

    private int viewingDistanceLeft(Tree tree) {
        int count = 1;
        while (true) {
            if (tree.x() - count < 0) {
                return count - 1;
            }
            Tree lookedAtTree = trees.get(tree.y()).get(tree.x() - count);
            if (lookedAtTree.height() >= tree.height()) {
                return count;
            }
            count++;
        }
    }

    private int viewingDistanceDown(Tree tree) {
        int count = 1;
        while (true) {
            if (tree.y() + count >= height) {
                return count - 1;
            }
            Tree lookedAtTree = trees.get(tree.y() + count).get(tree.x());
            if (lookedAtTree.height() >= tree.height()) {
                return count;
            }
            count++;
        }
    }

    private int viewingDistanceUp(Tree tree) {
        int count = 1;
        while (true) {
            if (tree.y() - count < 0) {
                return count - 1;
            }
            Tree lookedAtTree = trees.get(tree.y() - count).get(tree.x());
            if (lookedAtTree.height() >= tree.height()) {
                return count;
            }
            count++;
        }
    }
}
