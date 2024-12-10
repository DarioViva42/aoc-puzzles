package tk.vivas;

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

    public static long concatenate(long a, long b) {
        return Long.parseLong(String.valueOf(a) + b);
    }
}
