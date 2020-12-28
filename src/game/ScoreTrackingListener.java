package game;

import collection.HitListener;
import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * This class listens to the blocks' hits in the game, and raises the score accordingly.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * This method uses as a constructor.
     * @param scoreCounter - required in order to increases the current score of the game,
     * depending on the type of block hit.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method increases the score of the game depending on the type of block hit.
     * @param beingHit - the given block that is hit.
     * @param hitter - the given hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getNumOfHits() == 0) {
           this.currentScore.increase(15);
       } else {
           this.currentScore.increase(5);
       }
    }
}
