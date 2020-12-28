package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collection.Animation;
import collection.SpriteCollection;
import collection.GameEnvironment;
import collection.Collidable;
import collection.Sprite;
import collection.KeyPressStoppableAnimation;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import levels.LevelInformation;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import objects.Counter;
import objects.BackgroundColor;
import options.PauseScreen;
import java.awt.Color;

/**
 * This class creates and holds the sprites and collidables,
 * and initializes the GameLevel, and runs the animation.
 * @version 1.0 17 April 2018
 * @author Avi miletzky
 */
public class GameLevel implements Animation {

    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private Counter score;
    private Counter numOfLives;
    private Counter blocksCounter = new Counter(0);
    private Counter ballCounter = new Counter(0);
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTL;
    private Paddle paddle;

    public static final int HEADER_HEIGHT = 20;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int BORDER_BLOCK_WIDTH = 10;
    public static final int HEIGHT_OF_PADDLE = 12;
    public static final int RADIUS_OF_BALL = 5;

    /**
     * This method uses as a constructor.
     * @param level - the current level, in order to runs it.
     * @param kb - required in order to support the pause of the game, by pressed "p" key.
     * @param ar - the AnimationRunner that actually running the game loop.
     * @param score - the current score - which has so far accumulated in the game.
     * @param nol - the number of lives left for the game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor kb, AnimationRunner ar, Counter score,
                     Counter nol) {
        this.level = level;
        this.keyboard = kb;
        this.runner = ar;
        this.score = score;
        this.numOfLives = nol;
        this.scoreTL = new ScoreTrackingListener(score);
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.ballRemover = new BallRemover(this, this.ballCounter);
    }

    /**
     * This method adds Collidable(c) to the ENVIRONMENT list.
     * @param c - the given Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds Sprite(s) to the SPRITES list.
     * @param s - the given Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method returns the blocksCounter of the GameLevel.
     * @return this.blocksCounter;
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * This method returns the KeyboardSensor of the GameLevel.
     * @return this.keyboard;
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * This method removes the given Collidable from the ENVIRONMENT list of the game.
     * @param c - the given collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method removes the given Sprite from the SPRITES list of the game.
     * @param s - the given Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method initializes and creates all objects in the game: blocks, ball and paddle.
     */
    public void initialize() {
        this.sprites.addSprite(this.level.getBackground());
        this.blocksCounter.increase(level.numberOfBlocksToRemove());
        // Put the blocks into the game.
        for (Block block : this.level.blocks()) {
            Block b = new Block(block);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTL);
        }
        // Creates blocks that serve as boundaries.
        Block left = new Block(new Rectangle(new Point(0, HEADER_HEIGHT + BORDER_BLOCK_WIDTH),
                BORDER_BLOCK_WIDTH, WINDOW_HEIGHT - BORDER_BLOCK_WIDTH));
        left.setBackground(new BackgroundColor(Color.GRAY));
        left.addToGame(this);
        Block bottom = new Block(new Rectangle(new Point(0, WINDOW_HEIGHT),
                WINDOW_WIDTH, BORDER_BLOCK_WIDTH));
        bottom.setBackground(new BackgroundColor(Color.GRAY));
        bottom.addToGame(this);
        bottom.addHitListener(ballRemover);
        Block right = new Block(new Rectangle(new Point(WINDOW_WIDTH - BORDER_BLOCK_WIDTH,
                HEADER_HEIGHT + BORDER_BLOCK_WIDTH), BORDER_BLOCK_WIDTH,
                WINDOW_HEIGHT - BORDER_BLOCK_WIDTH));
        right.setBackground(new BackgroundColor(Color.GRAY));
        right.addToGame(this);
        Block up = new Block(new Rectangle(new Point(0, HEADER_HEIGHT), WINDOW_WIDTH,
                BORDER_BLOCK_WIDTH + 5));
        up.setBackground(new BackgroundColor(Color.GRAY));
        up.addToGame(this);
        Block header = new Block(new Rectangle(new Point(0, 0),
                WINDOW_WIDTH, HEADER_HEIGHT));
        header.setBackground(new BackgroundColor(new Color(0xBEBEBE)));
        header.addToGame(this);
        header.addHitListener(scoreTL);
        ScoreIndicator sI = new ScoreIndicator(score);
        sI.addToGame(this);
        LivesIndicator lI = new LivesIndicator(numOfLives);
        lI.addToGame(this);
        NameLevelIndicator nLi = new NameLevelIndicator(this.level.levelName());
        nLi.addToGame(this);
        // creates paddle
        this.paddle = new Paddle(new Rectangle(new Point((WINDOW_WIDTH - this.level.paddleWidth()) / 2 + 1,
                WINDOW_HEIGHT - HEIGHT_OF_PADDLE), this.level.paddleWidth(), HEIGHT_OF_PADDLE),
                this, Color.YELLOW, this.level.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * This method initializes the position of the paddle to the center of the screen.
     */
    private void centerThePaddle() {
        this.paddle.getCollisionRectangle().setUpperLeft(new Point(
                (WINDOW_WIDTH - this.level.paddleWidth()) / 2 + 1,
                WINDOW_HEIGHT - HEIGHT_OF_PADDLE));
    }

    /**
     * This method creates a number of balls corresponding to the current level,
     * and initializes their position to be on the center of the paddle.
     */
    private void createsBalls() {
        // Creates multiple bouncing balls.
        for (Velocity v : this.level.initialBallVelocities()) {
            Ball ball = new Ball(new Point(WINDOW_WIDTH / 2 + 1, (WINDOW_HEIGHT
                    - HEIGHT_OF_PADDLE - RADIUS_OF_BALL - 1)), RADIUS_OF_BALL, Color.WHITE);
            ball.setVelocity(v);
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * This method is used as a stop condition to finish drawing.
     * @return the current condition.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This method draws GameLevel on a given DrawSurface at each time it is called.
     * @param  d - the given DrawSurface.
     * @param  dt -
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        if (this.blocksCounter.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
            this.numOfLives.decrease(1);
        }
    }

    /**
     * This method initializes the balls and paddle at the center of the screen,
     * then runs the countdown and the current level of the game.
     */
    public void playOneTurn() {
        this.createsBalls();
        this.centerThePaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }
}