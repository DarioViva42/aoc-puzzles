package vivas.tk.adventofcode.day09;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RopeSimulation {

    private final Knot head;
    private final Knot tail;

    private final List<Instruction> instructions;

    private final Set<Point> visitedPoints;

    public RopeSimulation(String input) {
        instructions = input.lines()
                .map(Instruction::parse)
                .toList();

        head = new Knot(0, 0);
        tail = new Knot(0, 0);

        visitedPoints = new TreeSet<>();
        visitedPoints.add(tail.getPosition());
    }

    public int runSimulation() {
        instructions.forEach(this::executeInstruction);
        return visitedPoints.size();
    }

    private void executeInstruction(Instruction instruction) {
        for (int i = 0; i < instruction.amount(); i++) {
            head.move(instruction.direction());
            tail.fallowKnot(head, visitedPoints);
        }
    }
}
