package tk.vivas.adventofcode.year2024.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Report {

	private final List<Integer> levels;

	Report(String line) {
		levels = Arrays.stream(line.split(" "))
				.map(Integer::parseInt)
				.toList();
	}

	private Report(List<Integer> levels) {
		this.levels = levels;
	}

	boolean safe() {
		List<Integer> differences = new ArrayList<>();
		for (int i = 0; i < levels.size() - 1; i++) {
			int difference = levels.get(i) - levels.get(i + 1);
			differences.add(difference);
		}

		boolean increasing = differences.stream().allMatch(Report::increasing);
		boolean decreasing = differences.stream().allMatch(Report::decreasing);

		return increasing || decreasing;
	}

	private static boolean increasing(Integer difference) {
		return difference >= 1 && difference <= 3;
	}

	private static boolean decreasing(Integer difference) {
		return increasing(-difference);
	}

	boolean safeWithDampener() {
		if (safe()) {
			return true;
		}
		return IntStream.range(0, levels.size())
				.mapToObj(this::createDampenedReport)
				.anyMatch(Report::safe);
	}

	private Report createDampenedReport(int indexOfRemovableLevel) {
		List<Integer> copy = new ArrayList<>(levels);
		copy.remove(indexOfRemovableLevel);
		return new Report(copy);
	}
}
