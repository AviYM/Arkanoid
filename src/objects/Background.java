package objects;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * This class defines Background which can be drawn.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public interface Background {

    /**
     * This method gets a rectangle to draw inside.
     * @param rec - the Rectangle to draw inside.
     */
    void setRectangle(Rectangle rec);

    /**
     * This method draws background on the DrawSurface.
     * @param d - the given DrawSurface.
     */
    void drawOn(DrawSurface d);
}
