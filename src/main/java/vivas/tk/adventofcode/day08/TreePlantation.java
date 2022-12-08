package vivas.tk.adventofcode.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePlantation {

    private final List<List<Tree>> trees;
    private final List<Tree> visibleTrees;

    public TreePlantation(List<String> input) {
        this.trees = new ArrayList<>();
        for (byte y = 0; y < input.size(); y++) {
            List<Tree> temp = new ArrayList<>();
            String line = input.get(y);
            for (byte x = 0; x < line.length(); x++) {
                byte height = (byte) (line.charAt(x) - 48);
                temp.add(new Tree(x, y, height));
            }
            trees.add(temp);
        }
        visibleTrees = new ArrayList<>();
    }

    public long countVisibleTrees() {
        if (visibleTrees.isEmpty()) {
            addVisibleFromLeft();
            addVisibleFromRight();
            addVisibleFromTop();
            addVisibleFromBottom();
        }

        return visibleTrees.stream()
                .distinct()
                .count();
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
}
