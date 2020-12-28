package geometry;

import java.util.List;

/**
 * This class defines lines. And includes methods for calculating the midpoint and length of a line,
 * whether there is a point of intersection between two lines and what is,
 * and whether two lines are equal.
 * @version 1.0 15 April 2018
 * @author Avi miletzky
 */
public class Line {
    private Point start, end;

    /**
     * This method uses as a constructor #1. Which creates a line by 2 received points.
     * @param start - start point.
     * @param end - end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * This method uses as a constructor #2. Which creates a line by 4 received double.
     * @param x1 - The first coordinate of the starting point.
     * @param y1 - The second coordinate of the starting point.
     * @param x2 - The first coordinate of the end point.
     * @param y2 - The second coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * this method returns the length of the line.
     * @return the distance between the start and the end of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method returns the middle point of the line.
     * @return mid(dle)
     */
    public Point middle() {
        Point mid = new Point(((this.start.getX() + this.end.getX()) / 2),
                ((this.start.getY() + this.end.getY()) / 2));
        return mid;
    }

    /**
     * this method returns the start point of the line.
     * @return this.start
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method returns the end point of the line.
     * @return this.end
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method returns true if the lines intersect, false otherwise.
     * @param other - The second line for checking the intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point ret = this.intersectionWith(other);
        if (ret == null) {
            return false;
        }
        return true;
    }

    /**
     * This method returns the intersection point if the lines intersect by Cramer's rule,
     * and null otherwise.
     * @param other - The second line for checking the intersection.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {

        // Line THIS represented as a1x + b1y = c1
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());

        // Line OTHER represented as a2x + b2y = c2
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // In case THIS is a point, and OTHER is a line.
            if ((a1 == 0 && b1 == 0) && (a2 != 0 || b2 != 0)) {
                // Checks whether there is a intersection between them.
                if ((a2 * this.start.getX() + b2 * this.start.getY()) == c2) {
                    // Checks whether the intersection point in the line range.
                    if (other.checkPointInRangeOfLine(this.start)) {
                        return this.start;
                    }
                }
                // In case THIS is a line, and OTHER is a point.
            } else if ((a1 != 0 || b1 != 0) && (a2 == 0 && b2 == 0)) {
                // Checks whether there is a intersection between them.
                if ((a1 * other.start.getX() + b1 * other.start.getY()) == c1) {
                    // Checks whether the intersection point in the line range.
                    if (this.checkPointInRangeOfLine(other.start)) {
                        return other.start;
                    }
                }
                // In case both THIS and OTHER are points.
            } else if (this.equals(other)) {
                return this.start;
                // In case OTHER continues the THIS line from its end/start point, or vice versa.
            } else if (((this.start.getX() == other.start.getX())
                    && (this.start.getY() == other.start.getY()))
                    || ((this.start.getX() == other.end.getX())
                    && (this.start.getY() == other.end.getY()))) {
                return this.start;
                // In case THIS continues the OTHER line from its end/start point, or vice versa.
            } else if (((this.end.getX() == other.start.getX())
                    && (this.end.getY() == other.start.getY()))
                    || ((this.end.getX() == other.end.getX())
                    && (this.end.getY() == other.end.getY()))) {
                return this.end;
            }
            // In case the lines are parallel.
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersection = new Point(x, y);
            // Checks whether the intersection point in the lines range.
            if (this.checkPointInRangeOfLine(intersection)) {
                if (other.checkPointInRangeOfLine(intersection)) {
                    return intersection;
                }
            }
        }
        // In case the intersection point is out of range.
        return null;
    }

    /**
     * This method returns true if the lines are equal, false otherwise.
     * @param other - The second line for checking the intersection.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end()))) {
            return true;
        }
        return false;
    }

    /**
     * This method Checks whether the point is within range of the line.
     * @param p - Point for inspection.
     * @return true if the point is in line's range.
     */
    public boolean checkPointInRangeOfLine(Point p) {
        double epsilon = 0.001;
        if (((p.getX() >= (this.start.getX() - epsilon)) && (p.getX() <= (this.end.getX() + epsilon)))
                || ((p.getX() >= (this.end.getX() - epsilon)) && (p.getX() <= (this.start.getX() + epsilon)))) {
            if (((p.getY() >= (this.start.getY() - epsilon)) && (p.getY() <= (this.end.getY() + epsilon)))
                    || ((p.getY() >= (this.end.getY() - epsilon)) && (p.getY() <= (this.start.getY() + epsilon)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method Checks whether the line intersects with the rectangle.
     * @param rect - rectangle to check.
     * @return the closest intersection point to the start of the line, and null otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.size() == 0) {
            return null;
        } else if (pointList.size() == 1) {
            return pointList.get(0);
        }
        if (this.start.distance(pointList.get(0)) > this.start.distance(pointList.get(1))) {
            return pointList.get(1);
        }
        return pointList.get(0);
    }
}