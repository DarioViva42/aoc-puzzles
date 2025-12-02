package tk.vivas.adventofcode.year2025.day01;

import java.util.*;

class RotatingDial {

    private static final int DIAL_SIZE = 100;

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
            currentPosition = rotationCommand.direction()
                    .apply(currentPosition, rotationCommand.amount()) % DIAL_SIZE;
            if (currentPosition < 0) {
                currentPosition += DIAL_SIZE;
            }
            if (currentPosition == 0) {
                zeroCount++;
            }
        }
        return zeroCount;
    }

    public long countAllZeroPositions(int startPosition) {
        long zeroCount = 0;
        int currentPosition = startPosition;
        for (RotationCommand rotationCommand : rotationSequence) {
            boolean startedOnZero = currentPosition == 0;
            currentPosition = rotationCommand.direction()
                    .apply(currentPosition, rotationCommand.amount());
            if (currentPosition < 0) {
                while (currentPosition < 0) {
                    currentPosition += DIAL_SIZE;
                    zeroCount++;
                }
                if (startedOnZero) {
                    zeroCount--;
                }
            } else if (currentPosition > DIAL_SIZE) {
                while (currentPosition > DIAL_SIZE) {
                    currentPosition -= DIAL_SIZE;
                    zeroCount++;
                }
            }
            if (currentPosition == 0) {
                zeroCount++;
            }
            if (currentPosition == DIAL_SIZE) {
                currentPosition = 0;
                zeroCount++;
            }
        }
        return zeroCount;
    }
}
