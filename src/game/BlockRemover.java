package game;

import collection.HitListener;
import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * The class BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @version 1.0 17 Mars 2018
 * @author Avi miletzky
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * This method uses as a constructor.
     * @param game - required in order to remove blocks from specific game.
     * @param removedBlocks - the given counter with the current number of blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method removes blocks from the game that are hit and reach 0 hit-points.
     * @param beingHit - the given block to remove
     * @param hitter - the given hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}
