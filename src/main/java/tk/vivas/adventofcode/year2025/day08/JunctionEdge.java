package tk.vivas.adventofcode.year2025.day08;

record JunctionEdge(JunctionBox a, JunctionBox b) {
    long squaredDistance() {
        return a.squaredDistance(b);
    }

    int connect() {
        return a.connect(b);
    }

    long wallDistance() {
        return a.x() * b.x();
    }
}
