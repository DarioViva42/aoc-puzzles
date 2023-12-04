package tk.vivas.adventofcode.year2023.day04;

import java.util.HashMap;

public class CountingMap<E> extends HashMap<E, Integer> {
	public void add(E key, Integer value) {
		Integer currentValue = get(key);
		put(key, currentValue + value);
	}
}
