package tk.vivas.adventofcode.year2023.day16;

import java.util.Arrays;

enum ContraptionTile {
    EMPTY_SPACE('.'),
    MIRROR_NE_SW('\\'),
    MIRROR_NW_SE('/'),
    HORIZONTAL_SPLITTER('-'),
    VERTICAL_SPLITTER('|');

    private final char value;

    ContraptionTile(char value) {
        this.value = value;
    }

    static ContraptionTile from(char value) {
        return Arrays.stream(ContraptionTile.values())
                .filter(tile -> tile.value == value)
                .findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
