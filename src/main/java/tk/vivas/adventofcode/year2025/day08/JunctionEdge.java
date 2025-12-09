package tk.vivas.adventofcode.year2025.day08;

record JunctionEdge(JunctionBox a, JunctionBox b) {
    double distance() {
        return a.distance(b);
    }

    boolean connect() {
        return a.connect(b);
    }
}
