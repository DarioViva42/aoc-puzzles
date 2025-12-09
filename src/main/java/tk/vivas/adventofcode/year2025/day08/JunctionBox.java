package tk.vivas.adventofcode.year2025.day08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class JunctionBox {

    private final int x;
    private final int y;
    private final int z;
    private List<JunctionBox> connections;

    JunctionBox(String input) {
        String[] split = input.split(",");

        x = Integer.parseInt(split[0]);
        y = Integer.parseInt(split[1]);
        z = Integer.parseInt(split[2]);

        connections = new ArrayList<>();
        connections.add(this);
    }

    // √( ∆x² + ∆y² + ∆z² )
    double distance(JunctionBox other) {
        return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2));
    }

    boolean connect(JunctionBox other) {
        if (connections.contains(other)) {
            return false;
        }
        connections.addAll(other.connections);
        connections.forEach(box -> box.setConnections(connections));
        return true;
    }

    private void setConnections(List<JunctionBox> connections) {
        this.connections = connections;
    }

    List<JunctionBox> getConnections() {
        return connections;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof JunctionBox that)) return false;

        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}
