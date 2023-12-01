package tk.vivas.adventofcode.day10;

import java.util.Arrays;

public class CRT {

    private final boolean[] pixels;

    public CRT() {
        pixels = new boolean[40 * 6];
        Arrays.fill(pixels, false);
    }

    public void turnOn(int index) {
        pixels[index] = true;
    }

    public String render() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i]) {
                stringBuilder.append("#");
            } else {
                stringBuilder.append(".");
            }
            if ((i + 1) % 40 == 0) {
                stringBuilder.append("\n");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
