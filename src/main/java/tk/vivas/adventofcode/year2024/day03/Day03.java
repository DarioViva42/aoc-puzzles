package tk.vivas.adventofcode.year2024.day03;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day03 {
	public static void main(String[] args) {
		String input = readPuzzleInput();

		Instant start = Instant.now();

		CorruptedMemory corruptedMemory = new CorruptedMemory(input);

		Instant parseEnd = Instant.now();

		long partOneAnswer = corruptedMemory.runMulInstructions();

		Instant betweenParts = Instant.now();

		long partTwoAnswer = corruptedMemory.runAllInstructions();

		Instant end = Instant.now();

		sendPuzzleAnswer(1, partOneAnswer);
		sendPuzzleAnswer(2, partTwoAnswer);

		logDurations(start, parseEnd, betweenParts, end);
	}
}
