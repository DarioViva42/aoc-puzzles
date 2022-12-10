package vivas.tk.adventofcode.day09;

class Knot {

    int x;
    int y;

    public Knot() {
        x = 0;
        y = 0;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
    }

    private void moveUp() {
        y--;
    }

    private void moveDown() {
        y++;
    }

    private void moveLeft() {
        x--;
    }

    private void moveRight() {
        x++;
    }

    public void fallowKnot(Knot knot) {
        if (knot.y - y == 2) {
            fallowKnotDown(knot);
        } else if (y - knot.y == 2) {
            fallowKnotUp(knot);
        } else if (knot.x - x == 2) {
            fallowKnotRight(knot);
        } else if (x - knot.x == 2) {
            fallowKnotLeft(knot);
        }
    }

    private void fallowKnotUp(Knot knot) {
        if (knot.x > x) {
            moveRight();
        } else if (knot.x < x) {
            moveLeft();
        }
        moveUp();
    }

    private void fallowKnotDown(Knot knot) {
        if (knot.x > x) {
            moveRight();
        } else if (knot.x < x) {
            moveLeft();
        }
        moveDown();
    }

    private void fallowKnotRight(Knot knot) {
        if (knot.y > y) {
            moveDown();
        } else if (knot.y < y) {
            moveUp();
        }
        moveRight();
    }

    private void fallowKnotLeft(Knot knot) {
        if (knot.y > y) {
            moveDown();
        } else if (knot.y < y) {
            moveUp();
        }
        moveLeft();
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "Knot{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
