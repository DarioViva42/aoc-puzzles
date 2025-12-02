package tk.vivas;

import java.util.HashSet;
import java.util.Set;

public class MathUtils {

    private MathUtils() {
    }

    public static long lcm(long... numbers) {
        if (numbers.length == 0) {
            return 1;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        long lcm = 1;
        for (long number : numbers) {
            lcm = lcm(lcm, number);
        }
        return lcm;
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        long min = Math.min(a, b);
        long max = Math.max(a, b);
        long lcm = max;
        while (lcm % min != 0L) {
            lcm += max;
        }
        return lcm;
    }

    // see: https://www.baeldung.com/java-list-factors-integer#further-optimization---version-3
    public static Set<Integer> factors(int n) {
        Set<Integer> factors = new HashSet<>();
        int step = n % 2 == 0 ? 1 : 2;
        for (int i = 1; i <= Math.sqrt(n); i += step) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }
        return factors;
    }

    public static long concatenate(long a, long b) {
        double bLength = Math.floor(Math.log10(b)) + 1;
        return a * (long) Math.pow(10, bLength) + b;
    }
}
