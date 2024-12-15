package tk.vivas.adventofcode.year2024.day12;

record GardenGroupDetails(int area, int perimeter) {
    int calculateCost() {
        return area * perimeter;
    }
}
