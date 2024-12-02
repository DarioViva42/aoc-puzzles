package tk.vivas.adventofcode.year2024.day02;

import java.util.List;

class UnusualData {

	private final List<Report> reports;

	UnusualData(String input) {
		reports = input.lines()
				.map(Report::new)
				.toList();
	}

	long countSafeReports() {
		return reports.stream()
				.filter(Report::safe)
				.count();
	}

	long countSafeReportsWithDampener() {
		return reports.stream()
				.filter(Report::safeWithDampener)
				.count();
	}
}
