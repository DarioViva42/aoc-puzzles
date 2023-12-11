package tk.vivas.adventofcode.year2023.day11;

record Galaxy(long x, long y) {

	long distance(Galaxy other) {
		return Math.abs(other.x - x) + Math.abs(other.y - y);
	}
}
