package levels;

import collection.Sprite;
import geometry.Velocity;
import objects.Block;
import java.util.List;

/**
 * This class defines interface of LevelInformation, that holds all information of a specific level.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public interface LevelInformation {

    /**
     * This method returns the number of balls there is in this level.
     * (by: initialBallVelocities().size() == numberOfBalls()).
     * @return numberOfBalls
     */
    int numberOfBalls();

    /**
     * This method returns list with The initial velocity of each ball.
     * @return List<Velocity>
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method returns the speed of paddle.
     * @return speed of paddle.
     */
    int paddleSpeed();

    /**
     * This method returns the width of paddle.
     * @return width of paddle.
     */
    int paddleWidth();

    /**
     * This method returns the level's name.
     * @return the name of level.
     */
    String levelName();

    /**
     * This method returns a sprite with the background of the level.
     * @return the background of level.
     */
    Sprite getBackground();
    /**
     * This method returns list with all blocks of THIS level,
     * each block contains its size, color and location.
     * @return List<Block>
     */
    List<Block> blocks();
    /**
     * This method returns the number of blocks of THIS level.
     * @return blocks.size().
     */
    int numberOfBlocksToRemove();
}
