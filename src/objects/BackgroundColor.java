package objects;

import biuoop.DrawSurface;
import collection.Sprite;
import geometry.Rectangle;
import java.awt.Color;

/**
 * This class sets background color.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class BackgroundColor implements Sprite, Background {

    private Color color;
    private Rectangle rectangle;
    private boolean defaultRec;

    /**
     * This class sets background color.
     * @param color - the given color
     */
    public BackgroundColor(Color color) {
        this.color = color;
        this.defaultRec = true;
    }

    /**
     * This class draws background color inside the given Rectangle.
     * @param rec - the given Rectangle
     */
    public void setRectangle(Rectangle rec) {
        this.rectangle = rec;
        this.defaultRec = false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        if (this.defaultRec) {
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else {
            d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
    }

    @Override
    public void timePassed(double dt) {
        return;
    }
}