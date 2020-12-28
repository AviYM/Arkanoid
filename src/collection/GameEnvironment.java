package collection;

import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the game environment- a list of all objects that can be collided.
 * @version 1.0 15 April 2018
 * @author Avi miletzky
 */
public class GameEnvironment {

    private List<Collidable> objectsList = new ArrayList<>();

    /**
     * This method adds the given collidable object to the environment.
     * @param c - the given collidable.
     */
    public void addCollidable(Collidable c) {
        objectsList.add(c);
    }

    /**
     * This method removes the given Collidable from the environment.
     * @param c - the given collidable.
     */
    public void removeCollidable(Collidable c) {
        objectsList.remove(c);
    }

    /**
     * This method returns a specific collidable requested.
     * @param index - the index of object in the list.
     * @return the collidable requested.
     */
    public Collidable getCollidable(int index) {
        return objectsList.get(index);
    }

    /**
     * This method is looking for closest collision that is going to occur,
     * between the ball and any collidable in case there is.
     * @param trajectory - a ball moving from line.start() to line.end().
     * @return the information about the closest collision that is going to occur, or null if there is no.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestP = null;
        Collidable closestC = null;
        for (int i = 0; i < objectsList.size(); i++) {
            Collidable tempC = this.getCollidable(i);
            Point tempP = trajectory.closestIntersectionToStartOfLine(tempC.getCollisionRectangle());
            if (tempP == null) {
                continue;
            }
            if ((closestC == null)
                    || (trajectory.start().distance(tempP) < trajectory.start().distance(closestP))) {
                closestC = tempC;
                closestP = tempP;
            }
        }
        if (closestC == null) {
            return null;
        }
        return new CollisionInfo(closestP, closestC);
    }
}
