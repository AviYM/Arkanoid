package objects;

import biuoop.DrawSurface;
import collection.Sprite;
import geometry.Rectangle;
import java.awt.Image;

/**
 * This class sets background image.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class BackgroundImage implements Sprite, Background {

    private Image image;
    private Rectangle rectangle;
    private boolean defaultRec;

    /**
     * This class sets background image.
     * @param image - the given image.
     */
    public BackgroundImage(Image image) {
        this.image = image;
        this.defaultRec = true;
    }

    /**
     * This class draws background image inside the given Rectangle.
     * @param rec - the given Rectangle
     */
    public void setRectangle(Rectangle rec) {
        this.rectangle = rec;
        this.defaultRec = false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.defaultRec) {
            d.drawImage(0, 0, this.image);
        } else {
            d.drawImage((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), this.image);
        }
    }

    @Override
    public void timePassed(double dt) {
        return;
    }
}