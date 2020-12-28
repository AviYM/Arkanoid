package objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collection.Collidable;
import collection.Sprite;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import java.awt.Color;

/**
 * This class defines the paddle as a rectangle with color that is controlled by the arrow keys,
 * and moves according to the player key presses.
 * @version 1.0 18 April 2018
 * @author Avi miletzky
 */
public class Paddle implements Sprite, Collidable {

    private Rectangle rect;
    private Color color;
    private Velocity velocity;
    private GameLevel game;
    private double speed;
    private double dt;

    /**
     * This method uses as a constructor of paddle.
     * @param r - The given Rectangle-shape of paddle.
     * @param game - The given game - needed in order to control on paddle movement, by keyboard' arrows.
     * @param c - The given Color of paddle.
     * @param speed - the given speed of the paddle
     */
    public Paddle(Rectangle r, GameLevel game, Color c, double speed) {
        this.rect = r;
        this.color = c;
        this.game = game;
        this.speed = speed;
    }

    /**
     * This method defines the movement velocity of the paddle, by received velocity.
     * In the absence of this setting the paddle will not move in the window.
     * @param v - The given velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method moves the paddle one step to the left side.
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() > GameLevel.BORDER_BLOCK_WIDTH) {
            this.setVelocity(new Velocity(-(this.speed * this.dt), 0));
            Point newLocation = this.velocity.applyToPoint(this.getCollisionRectangle().getUpperLeft());
            this.getCollisionRectangle().setUpperLeft(newLocation);
        } else {
            this.getCollisionRectangle().setUpperLeft(new Point(
                    0 + GameLevel.BORDER_BLOCK_WIDTH,
                    this.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * This method moves the paddle one step to the right side.
     */
    public void moveRight() {
        if ((this.getCollisionRectangle().getUpperLeft().getX()
                + this.getCollisionRectangle().getWidth())
                < GameLevel.WINDOW_WIDTH - GameLevel.BORDER_BLOCK_WIDTH) {
            this.setVelocity(new Velocity(this.speed * this.dt, 0));
            Point newLocation = this.velocity.applyToPoint(this.getCollisionRectangle().getUpperLeft());
            this.getCollisionRectangle().setUpperLeft(newLocation);
        } else {
            this.getCollisionRectangle().setUpperLeft(new Point(
                    GameLevel.WINDOW_WIDTH - GameLevel.BORDER_BLOCK_WIDTH
                            - this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * This method moves the paddle each time to the right or left according to the pressed key.
     * @param dt1 -
     */
    public void timePassed(double dt1) {
        this.dt = dt1;
        if (this.game.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.game.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * This method draws the paddle on the window by DrawSurface.
     * @param d - the given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight() - 4);
    }

    /**
     * This method returns the RECT of paddle.
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter - a given hitter
     * @param collisionPoint - the given collision point.
     * @param currentVelocity - the given velocity.
     * @return the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double currSpeed = Math.sqrt(Math.pow(currentVelocity.getVelocityDx(), 2)
                + Math.pow(currentVelocity.getVelocityDy(), 2));
        for (int i = 0; i < 4; i++) {
            if (this.rect.getSidesArr()[i].checkPointInRangeOfLine(collisionPoint)) {
                if (i == 0 || i == 2) {
                    return new Velocity(-1 * currentVelocity.getVelocityDx(),
                            currentVelocity.getVelocityDy());
                }
                if (collisionPoint.getX() < (this.rect.getUpperLeft().getX()
                        + this.rect.getWidth() / 5)) {
                    currentVelocity = Velocity.fromAngleAndSpeed(300, currSpeed);
                } else if (collisionPoint.getX() < (this.rect.getUpperLeft().getX()
                        + 2 * (this.rect.getWidth() / 5))) {
                    currentVelocity = Velocity.fromAngleAndSpeed(330, currSpeed);
                } else if (collisionPoint.getX() < (this.rect.getUpperLeft().getX()
                        + 3 * (this.rect.getWidth() / 5))) {
                    currentVelocity = new Velocity(currentVelocity.getVelocityDx(),
                            -1 * currentVelocity.getVelocityDy());
                } else if (collisionPoint.getX() < (this.rect.getUpperLeft().getX()
                        + 4 * (this.rect.getWidth() / 5))) {
                    currentVelocity = Velocity.fromAngleAndSpeed(30, currSpeed);
                } else {
                    currentVelocity = Velocity.fromAngleAndSpeed(60, currSpeed);
                }
            }
        }
        return currentVelocity;
    }

    /**
     * This method adds THIS paddle to the game.
     * @param g - the given Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
