package tk.vivas.adventofcode.year2023.day19;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

record MachinePart(int x, int m, int a, int s) {

    static MachinePart from(String raw) {
        int x = extractRating('x', raw);
        int m = extractRating('m', raw);
        int a = extractRating('a', raw);
        int s = extractRating('s', raw);
        return new MachinePart(x, m, a, s);
    }

    private static int extractRating(char category, String input) {
        Pattern pattern = Pattern.compile(category + "=(\\d+)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new IllegalStateException("machine part misses rating for category: " + category);
        }
        String rating = matcher.group(1);
        return Integer.parseInt(rating);
    }

    long totalRanking() {
        return (long) x + m + a + s;
    }
}
