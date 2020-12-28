package levels;

import collection.Sprite;
import geometry.Velocity;
import objects.Block;
import java.util.List;

/**
 * This class implements the LevelInformation.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class Level implements LevelInformation {

    private List<Velocity> ballVelocities;
    private List<Block> blocks;
    private String levelName;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private int numOfBlocks;

    /**
     * This method sets BallVelocities.
     * @param newVelocities - the given newVelocities
     */
    public void setBallVelocities(List<Velocity> newVelocities) {
        this.ballVelocities = newVelocities;
    }

    /**
     * This method sets Blocks.
     * @param newBlocks - the given newBlocks
     */
    public void setBlocks(List<Block> newBlocks) {
        this.blocks = newBlocks;
    }

    /**
     * This method sets LevelName.
     * @param newNameOfLevel - newNameOfLevel
     */
    public void setLevelName(String newNameOfLevel) {
        this.levelName = newNameOfLevel;
    }

    /**
     * This method sets Background.
     * @param newBackground - newBackground
     */
    public void setBackground(Sprite newBackground) {
        this.background = newBackground;
    }

    /**
     * This method sets PaddleSpeed.
     * @param newPaddleSpeed - newPaddleSpeed
     */
    public void setPaddleSpeed(int newPaddleSpeed) {
        this.paddleSpeed = newPaddleSpeed;
    }

    /**
     * This method sets PaddleWidth.
     * @param newPaddleWidth - newPaddleWidth
     */
    public void setPaddleWidth(int newPaddleWidth) {
        this.paddleWidth = newPaddleWidth;
    }

    /**
     * This method sets NumOfBlocks.
     * @param newNumOfBlocks - newNumOfBlocks
     */
    public void setNumOfBlocks(int newNumOfBlocks) {
        this.numOfBlocks = newNumOfBlocks;
    }

    /**
     * This method returns the number of balls there is in this level.
     * (by: initialBallVelocities().size() == numberOfBalls()).
     * @return numberOfBalls
     */
    public int numberOfBalls() {
        return this.ballVelocities.size();
    }

    /**
     * This method returns list with The initial velocity of each ball.
     * @return List<Velocity>
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * This method returns the speed of paddle.
     * @return speed of paddle.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * This method returns the width of paddle.
     * @return width of paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * This method returns the level's name.
     * @return the name of level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * This method returns a sprite with the background of the level.
     * @return the background of level.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * This method returns list with all blocks of THIS level,
     * each block contains its size, color and location.
     * @return List<Block>
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * This method returns the number of blocks of THIS level.
     * @return blocks.size().
     */
    public int numberOfBlocksToRemove() {
        if (this.numOfBlocks > this.blocks.size()) {
            return this.blocks.size();
        }
        return this.numOfBlocks;
    }
}