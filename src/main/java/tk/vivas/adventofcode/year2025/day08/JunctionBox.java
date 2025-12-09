package tk.vivas.adventofcode.year2025.day08;

import java.util.ArrayList;
import java.util.List;

class JunctionBox {

    private final long x;
    private final long y;
    private final long z;
    private List<JunctionBox> connections;

    JunctionBox(String input) {
        String[] split = input.split(",");

        x = Long.parseLong(split[0]);
        y = Long.parseLong(split[1]);
        z = Long.parseLong(split[2]);

        connections = new ArrayList<>();
        connections.add(this);
    }

    // √( ∆x² + ∆y² + ∆z² )
    double distance(JunctionBox other) {
        return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2));
    }

    int connect(JunctionBox other) {
        if (!connections.contains(other)) {
            connections.addAll(other.connections);
            connections.forEach(box -> box.setConnections(connections));
        }
        return connections.size();
    }

    private void setConnections(List<JunctionBox> connections) {
        this.connections = connections;
    }

    List<JunctionBox> getConnections() {
        return connections;
    }

    long x() {
        return x;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof JunctionBox that)) return false;

        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(x);
        result = 31 * result + Long.hashCode(y);
        result = 31 * result + Long.hashCode(z);
        return result;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}
