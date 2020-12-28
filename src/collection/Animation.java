package collection;

import biuoop.DrawSurface;

/**
 * This class defines interface of animations which occur every time they are called a single action.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public interface Animation {

    /**
     * This method draws animation on a given DrawSurface at each time it is called.
     * @param  d - the given DrawSurface.
     * @param dt -
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * This method is used as a stop condition to finish drawing.
     * @return the current condition.
     */
    boolean shouldStop();
}
