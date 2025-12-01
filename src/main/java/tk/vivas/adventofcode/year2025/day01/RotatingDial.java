package tk.vivas.adventofcode.year2025.day01;

import java.util.*;

class RotatingDial {

    private final List<RotationCommand> rotationSequence;

    RotatingDial(String input) {
        rotationSequence = input.lines()
                .map(RotationCommand::new)
                .toList();
     }

    public int countZeroPositions(int startPosition) {
        int zeroCount = 0;
        int currentPosition = startPosition;
        for (RotationCommand rotationCommand : rotationSequence) {
            currentPosition = rotationCommand.direction().apply(currentPosition, rotationCommand.amount());
            if (currentPosition == 0) {
                zeroCount++;
            }
        }
        return zeroCount;
    }
}
