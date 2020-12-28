package geometry;

/**
 * This class defines speed and direction of ball movement.
 * @version 1.0 04 April 2018
 * @author Avi miletzky
 */
public class Velocity {

    private double dx, dy;

    /**
     * This method is used as a constructor #1. Which receives velocity as a change in the cartesian axis.
     * @param dx - The change on the X axis.
     * @param dy - The change on the Y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method returns the private dx.
     * @return double - dx.
     */
    public double getVelocityDx() {
        return this.dx;
    }

    /**
     * This method returns the private dy.
     * @return double - dy.
     */
    public double getVelocityDy() {
        return this.dy;
    }

    /**
     * This method returns true if the lines intersect, false otherwise.
     * @param p - a point with currently position (x,y).
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * This method is used as a constructor #2, Which receives velocity as angle and vector,
     * and converts to dx and dy.
     * @param angle - the angle.
     * @param speed - the vector.
     * @return Velocity as dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}

