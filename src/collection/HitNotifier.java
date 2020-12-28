package collection;

/**
 * This class defines interface of HitNotifier - An object that updates its listeners
 * with relevant information about hits in the game.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public interface HitNotifier {
    /**
     * This method adds hl as a listener to hit events.
     * @param  hl - the given HitListener.
     */
    void addHitListener(HitListener hl);
    /**
     * This method removes hl from the list of listeners to hit events.
     * @param hl - the given HitListener.
     */
    void removeHitListener(HitListener hl);
}
