package tk.vivas.adventofcode.year2023.day18;

enum DigTileType {
    NORTH_EAST(" └"),
    NORTH_SOUTH(" │"),
    NORTH_WEST("─┘"),
    EAST_SOUTH(" ┌"),
    EAST_WEST("──"),
    SOUTH_WEST("─┐"),
    EMPTY("  ");

    private final String displayString;

    DigTileType(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
