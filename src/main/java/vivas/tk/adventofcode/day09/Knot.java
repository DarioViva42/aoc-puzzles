package vivas.tk.adventofcode.day09;

import java.util.Set;

public class Knot {

    int x;
    int y;

    public Knot(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void fallowKnot(Knot knot, Set<Point> visitedPoints) {
        if (knot.y - y == 2) {
            fallowKnotDown(knot);
            visitedPoints.add(getPosition());
        } else if (y - knot.y == 2) {
            fallowKnotUp(knot);
            visitedPoints.add(getPosition());
        } else if (knot.x - x == 2) {
            fallowKnotRight(knot);
            visitedPoints.add(getPosition());
        } else if (x - knot.x == 2) {
            fallowKnotLeft(knot);
            visitedPoints.add(getPosition());
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
