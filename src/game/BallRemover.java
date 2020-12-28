package game;

import collection.HitListener;
import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * The class BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 * @version 1.0 17 Mars 2018
 * @author Avi miletzky
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * This method uses as a constructor.
     * @param game - required in order to remove balls from specific game.
     * @param removedBalls - the given counter with the current number of balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * This method removes blocks from the game that are hit and reach 0 hit-points.
     * @param beingHit - the given block to remove
     * @param hitter - the given hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
