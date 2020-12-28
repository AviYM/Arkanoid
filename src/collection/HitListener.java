package collection;

import objects.Ball;
import objects.Block;

/**
 * This class defines interface of HitListener - An object that listens to relevant information
 * about hits in its environment.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * the hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - The object that is hit.
     * @param hitter - The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
