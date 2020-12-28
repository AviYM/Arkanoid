package collection;

import geometry.Point;

/**
 * This class defines object Which contains information about a ball's collision point in the object,
 * and the object involved.
 * @version 1.0 15 April 2018
 * @author Avi miletzky
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * This method uses as a constructor of the CollisionInfo.
     * @param point - the collision point.
     * @param object - the object involved.
     */
    public CollisionInfo(Point point, Collidable object) {
        this.collisionPoint = point;
        this.collisionObject = object;
    }

    /**
     * This method returns the point at which the collision occurs.
     * @return this.collisionPoint
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * This method returns the collidable object involved in the collision..
     * @return this.collisionObject.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}