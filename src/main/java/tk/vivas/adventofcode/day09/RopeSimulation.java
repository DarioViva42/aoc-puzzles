package tk.vivas.adventofcode.day09;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

class RopeSimulation {

    private final List<Instruction> instructions;
    private Knot head;
    private List<Knot> tail;
    private Set<Point> visitedPoints;

    public RopeSimulation(String input) {
        instructions = input.lines()
                .map(Instruction::parse)
                .toList();
    }

    public int runSimulation(int numberOfKnots) {
        head = new Knot();
        tail = Stream.generate(Knot::new)
                .limit(numberOfKnots)
                .toList();

        visitedPoints = new TreeSet<>();
        visitedPoints.add(new Point(0, 0));

        instructions.forEach(this::executeInstruction);
        return visitedPoints.size();
    }

    private void executeInstruction(Instruction instruction) {
        for (int i = 0; i < instruction.amount(); i++) {
            head.move(instruction.direction());
            moveTail();
        }
    }

    private void moveTail() {
        boolean moved = tail.get(0).fallowKnot(head);
        for (int t = 1; t < tail.size(); t++) {
            if (!moved) break;
            moved = tail.get(t).fallowKnot(tail.get(t - 1));
        }
        if (moved) {
            Point lastPosition = tail.get(tail.size() - 1).getPosition();
            visitedPoints.add(lastPosition);
        }
    }
}
