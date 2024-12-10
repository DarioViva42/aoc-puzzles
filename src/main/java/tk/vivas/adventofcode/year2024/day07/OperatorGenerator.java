package tk.vivas.adventofcode.year2024.day07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class OperatorGenerator {
	private final int size;
	private final int optionCount;

	OperatorGenerator(int size, int optionCount) {
		this.size = size;
		this.optionCount = optionCount;
	}

	Stream<Operator[]> generate() {
		return IntStream.range(0, (int) Math.pow(optionCount, size))
				.mapToObj(this::createOperatorOption);
	}

	private Operator[] createOperatorOption(int optionIndex) {
		Operator[] operators = new Operator[size];
		Arrays.fill(operators, Operator.PLUS);

		List<Operator> option = Integer.toString(optionIndex, optionCount).chars()
				.mapToObj(Operator::from)
				.toList()
				.reversed();

		for (int i = 0; i < option.size(); i++) {
			operators[i] = option.get(i);
		}
		return operators;
	}
}
