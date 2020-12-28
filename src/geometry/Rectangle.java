package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * This class -.
 * @version 1.0 16 April 2018
 * @author Avi miletzky
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
    private Line[] sidesArr;

    /**
     * This method uses as a constructor of Rectangle.
     * and create a new rectangle with location and width/height.
     * @param upperLeft - The upper left corner of the rectangle.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        sidesArr = new Line[4];
        setSidesArr();
    }

    /**
     * This method Checks whether the specified line intersects with the rectangle.
     * @param line - the given line.
     * @return list of all intersection points between the rectangle and the line, and empty list otherwise.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();
        // Looking for intersection points.
        for (int i = 0, j = 0; i < 4; i++) {
            Point intersection = this.getSidesArr()[i].intersectionWith(line);
            if (intersection != null) {
                pointList.add(j, intersection);
                    j++;
            }
        }
        return pointList;
    }

    /**
     * This method returns the width of the rectangle.
     * @return this.width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method returns the height of the rectangle.
     * @return this.height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This method returns the upper left corner of the rectangle.
     * @return this.upperLeft.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method is intended to update the upper left corner of the rectangle, and all its sides.
     * @param newUpperLeft - the new UpperLeft.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
        this.setSidesArr();
    }

    /**
     * This method returns the sides array of the rectangle.
     * @return this.sidesArr.
     */
    public Line[] getSidesArr() {
        return this.sidesArr;
    }

    /**
     * This method defines all sides of the rectangle, by uses in UpperLeft, width and height.
     */
    public void setSidesArr() {
        // The sides of the rectangle, from the positive X axis and counterclockwise.
        this.sidesArr[0] = new Line(upperLeft, new Point(upperLeft.getX(), height + upperLeft.getY()));
        this.sidesArr[1] = new Line(new Point(upperLeft.getX(), height + upperLeft.getY()),
                new Point(width + upperLeft.getX(), height + upperLeft.getY()));
        this.sidesArr[2] = new Line(new Point(width + upperLeft.getX(), upperLeft.getY()),
                new Point(width + upperLeft.getX(), height + upperLeft.getY()));
        this.sidesArr[3] = new Line(upperLeft, new Point(width + upperLeft.getX(), upperLeft.getY()));
    }
}
