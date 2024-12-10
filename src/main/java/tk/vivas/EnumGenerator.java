package tk.vivas;

import java.lang.reflect.Array;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EnumGenerator<T extends Enum<T>> {
    private final int size;
    private final Class<T> enumClass;
    private final T[] enumConstants;
    private final int optionCount;
    private final int permutationCount;
    private final int[] digitValues;

    public EnumGenerator(Class<T> enumClass, int size) {
        this(enumClass, size, enumClass.getEnumConstants());
    }

    @SafeVarargs
    public EnumGenerator(Class<T> enumClass, int size, T... enumConstants) {
        this.size = size;
        this.enumClass = enumClass;

        this.enumConstants = enumConstants;
        optionCount = enumConstants.length;
        permutationCount = (int) Math.pow(optionCount, size);

        digitValues = IntStream.range(0, size)
                .map(i -> (int) Math.pow(optionCount, size - 1 - i))
                .toArray();
    }

    public Stream<T[]> generate() {
        return IntStream.range(0, permutationCount)
                .mapToObj(this::createPermutation);
    }

    @SuppressWarnings("unchecked")
    private T[] createPermutation(int optionIndex) {
        T[] operators = (T[]) Array.newInstance(enumClass, size);

        int temp = optionIndex;
        for (int i = 0; i < size; i++) {
            int digitValue = digitValues[i];
            int value = temp / digitValue;
            operators[i] = enumConstants[value];
            temp -= value * digitValue;
        }

        return operators;
    }
}
