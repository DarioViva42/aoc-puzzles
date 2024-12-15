package tk.vivas.adventofcode.year2024.day12;

class GardenGroupDetails {
    private int area;
    private int perimeter;

    public GardenGroupDetails() {
        this.area = 0;
        this.perimeter = 0;
    }

    void addTile() {
        area++;
        perimeter += 4;
    }

    void removePerimeters(int amount) {
        perimeter -= amount;
    }

    int calculateCost() {
        return area * perimeter;
    }
}
