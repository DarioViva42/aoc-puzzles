package tk.vivas.adventofcode.year2023.day06;

import static java.lang.Math.*;

record RaceRecord(long duration, long distance) {

    long marginOfError() {
        // distance < (timeToLoad) * (duration - timeToLoad)
        // distance < timeToLoad * duration - timeToLoad²
        // -d > x² - tx
        // 0 < ax² + bx + c
        // x = (-b ± √(b²-4ac)) / 2a
        
        double b = -duration;

        double sqrt = sqrt(b * b - 4 * (double) distance);
        double x1 = (-b + sqrt) / 2;
        double x2 = (-b - sqrt) / 2;

        double min = min(x1, x2);
        long minInt = (long) Math.ceil(min);
        if (minInt == min) {
            minInt++;
        }

        double max = max(x1, x2);
        long maxInt = (long) Math.floor(max);
        if (maxInt == max) {
            maxInt--;
        }

        return maxInt - minInt + 1;
    }
}
