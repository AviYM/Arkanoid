package objects;

import biuoop.DrawSurface;
import collection.Collidable;
import collection.CollisionInfo;
import collection.GameEnvironment;
import collection.Sprite;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import java.awt.Color;

/**
 * This class defines a ball in the received color and size.
 * And defines the movement trajectory of the ball by adding Velocity to the center point.
 * @version 2.0 20 April 2018
 * @author Avi miletzky
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gE;

    /**
     * This method uses as a constructor #1. creates a ball in the received color,
     * and its size is a radius in each direction from the center point.
     * @param center - The center point of the ball (changes as the ball moves).
     * @param r - The radios of the ball.
     * @param color - The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * This method uses as a constructor #2. creates a ball in the received color,
     * and its size is a radius in each direction from the two coordinate(x,y) of the center point.
     * @param x - The first coordinate of the center point.
     * @param y - The second coordinate of the center point.
     * @param r - The radios of the ball.
     * @param color - The color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * This method returns the first coordinate of the center point.
     * @return this.center.getX()
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This method returns the second coordinate of the center center.
     * @return this.center.getY()
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This method returns the radios of the ball.
     * @return this.radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * This method returns the color of the ball.
     * @return this.color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method draws the ball on the given DrawSurface.
     * @param surface - The draw Surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(new Color(0x000000));
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * This method moves the ball each time one step by read to moveOneStep.
     * @param dt -
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * This method adds THIS ball to a given game.
     * @param g - The given Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This method defines the movement velocity of the ball, by received velocity.
     * In the absence of this setting the ball will not move in the window.
     * @param v - The given velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method defines the movement velocity of the ball, by the change in the cartesian axis.
     * In the absence of this setting the ball will not move in the window.
     * @param dx - The change on the X axis.
     * @param dy - The change on the Y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * This method returns the ball's velocity.
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method defines the game environment of the ball with all obstacles.
     * @param e - The given GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment e) {
        this.gE = e;
    }

    /**
     * This method checks whether the ball stuck in a block or the paddle.
     * @param object - the given Collidable.
     * @param current - the current position of the ball.
     * @return true if it Stuck in a Collidable, and false otherwise.
     */
    private boolean ballInBlock(Collidable object, Point current) {
        if ((current.getX() >= object.getCollisionRectangle().getUpperLeft().getX())
                && (current.getX() <= object.getCollisionRectangle().getUpperLeft().getX()
                + object.getCollisionRectangle().getWidth())) {
            if ((current.getY() >= object.getCollisionRectangle().getUpperLeft().getY())
                    && (current.getY() <= object.getCollisionRectangle().getUpperLeft().getY()
                    + object.getCollisionRectangle().getHeight())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method gets out the ball if it stuck in a block or the paddle.
     * @param object - the given Collidable.
     * @param current - the current position of the ball.
     * @return the new position of the ball outside that Collidable, If he was stuck.
     * and the current position otherwise.
     */
    public Point getOutOfTheRectangle(Collidable object, Point current) {
        Point closest;
        if (ballInBlock(object, current)) {
            closest = new Point(current.getX(),
                    object.getCollisionRectangle().getSidesArr()[3].start().getY() - 1);
            /*if (object instanceof Paddle) {
                return closest;
            }*/
            // Check distance between center of the ball and each side of object.
            double d1 = current.distance(new Point(current.getX(),
                    object.getCollisionRectangle().getSidesArr()[3].start().getY()));
            double d2 = current.distance(new Point(current.getX(),
                    object.getCollisionRectangle().getSidesArr()[1].start().getY()));
            double d3 = current.distance(new Point(object.getCollisionRectangle().getSidesArr()[0].start().getX(),
                    current.getY()));
            double d4 = current.distance(new Point(object.getCollisionRectangle().getSidesArr()[2].start().getX(),
                    current.getY()));
            // Looking for the nearest side to go out to.
            double min = d1;
             if (d2 < min) {
                min = d2;
                closest = new Point(current.getX(),
                        object.getCollisionRectangle().getSidesArr()[1].start().getY() + 1);
             }
             if (d3 < min) {
                min = d3;
                closest = new Point(object.getCollisionRectangle().getSidesArr()[0].start().getX() - 1,
                        current.getY());
             }
             if (d4 < min) {
                closest = new Point(object.getCollisionRectangle().getSidesArr()[2].start().getX() + 1,
                        current.getY());
             }
             return closest;
        }
        return current;
    }

    /**
     * This method plans the next step of the ball and creates a trajectory.
     * and checks its environment to see if moving on this trajectory will hit anything.
     * if no, it take the ball to the end of the trajectory.
     * and if there is a hit, it moves the ball to "almost" the hit point.
     * and notifies the hit object that a collision occurred.
     * then updates the velocity, to the new velocity returned by the hit() method.
     * @param dt -
     */
    public void moveOneStep(double dt) {
        if (this.velocity.getVelocityDx() == 0 && this.velocity.getVelocityDy() == 0) {
            System.out.println("Error: No velocity defined.");
        }
        Velocity v = new Velocity(dt * this.velocity.getVelocityDx(),
                dt * this.velocity.getVelocityDy());
        Line trajectory = new Line(this.center, v.applyToPoint(this.center));
        CollisionInfo cI = this.gE.getClosestCollision(trajectory);
        if (cI == null) {
            this.center = v.applyToPoint(this.center);
            return;
        }
        double x = cI.collisionPoint().getX(), y = cI.collisionPoint().getY(), epsilon = 0.05;
        if (x > this.center.getX()) {
            x -= epsilon;
        } else {
            x += epsilon;
        }
        if (y > this.center.getY()) {
            y -= epsilon;
        } else {
            y += epsilon;
        }
        Point almostHitPoint = new Point(x, y);
        this.center = almostHitPoint;
        this.center = getOutOfTheRectangle(cI.collisionObject(), this.center);
        this.velocity = cI.collisionObject().hit(this, cI.collisionPoint(), this.velocity);
    }

    /**
     * This method removes ball from the game.
     * @param g - The given Game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}