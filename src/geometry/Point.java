package geometry;

/**
 * This class creates a point in a plane with two coordinates.
 * Checks two points whether they are equal and calculates a distance between two points.
 * @version 1.0 4 April 2018
 * @author Avi miletzky
 */
public class Point {

    private double x, y;

    /**
     * This method uses as a constructor. creates a point in a cartesian axis system
     * @param x - Position relative to the X-axis.
     * @param y - Position relative to the Y-axis.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method measures the distance between THIS point and OTHER point.
     * @param other - another point to measure.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
    }

    /**
     * This method Checks whether the point's values are equal.
     * @param other - Point for inspection.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.x) {
            if (this.y == other.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method return the x value of this point.
     * @return this.x
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method return the y value of this point.
     * @return this.y
     */
    public double getY() {
        return this.y;
    }
}

