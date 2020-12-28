package collection;

import biuoop.DrawSurface;

/**
 * This interface can be drawn on the screen, and can be notified that time has passed.
 * @version 1.0 17 April 2018
 * @author Avi miletzky
 */
public interface Sprite {

    /**
     * This method draw the sprite to the screen.
     * @param d - the given DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed.
     * @param dt -
     */
    void timePassed(double dt);
}
