package org.anjeyy.soba.common;

public record Coordinate(double x, double y) {

    public static Coordinate from(double x, double y) {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
