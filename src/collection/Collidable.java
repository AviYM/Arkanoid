package collection;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import objects.Ball;

/**
 * This interface defines objects that can be collided.
 * And includes their ability to correct the path of the object that collides with them.
 * @version 1.0 17 April 2018
 * @author Avi miletzky
 */
public interface Collidable {

    /**
     * This method Return the "collision shape" of the object.
     * @return this.rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * This method Checks where the collision point is located on the sides of the object.
     * and returns the new velocity expected after the hit(based on the current velocity).
     * @param hitter - The Ball that's doing the hitting.
     * @param collisionPoint - a given collision point
     * @param currentVelocity - a given velocity
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}