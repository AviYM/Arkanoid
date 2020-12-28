package objects;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * This class defines Stroke to block background.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class Stroke {

    private Color color;

    /**
     * This method creates Stroke to block background in the resulting color.
     * @param color - the given color.
     */
    public Stroke(Color color) {
        this.color = color;
    }

    /**
     * This method draws the Stroke on the given Rectangle of block.
     * @param d - the given DrawSurface.
     * @param rect - the given Rectangle.
     */
    public void draw(DrawSurface d, Rectangle rect) {
        d.setColor(this.color);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
}