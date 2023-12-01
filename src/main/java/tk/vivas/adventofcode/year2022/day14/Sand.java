package tk.vivas.adventofcode.year2022.day14;

public class Sand {
    private int x;
    private int y;

    public Sand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
        y++;
    }

    public void moveRight() {
        x++;
        y++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
