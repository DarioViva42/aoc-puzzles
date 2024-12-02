package tk.vivas.adventofcode.year2024.day02;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

public class Day02 {
	public static void main(String[] args) {
		String input = readPuzzleInput();

		Instant start = Instant.now();

		UnusualData unusualData = new UnusualData(input);

		Instant parseEnd = Instant.now();

		long partOneAnswer = unusualData.countSafeReports();

		Instant betweenParts = Instant.now();

		long partTwoAnswer = unusualData.countSafeReportsWithDampener();

		Instant end = Instant.now();

		sendPuzzleAnswer(1, partOneAnswer);
		sendPuzzleAnswer(2, partTwoAnswer);

		logDurations(start, parseEnd, betweenParts, end);
	}
}
