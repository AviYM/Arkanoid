package collection;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines SpriteCollection- a list of all objects which change their
 * position/ shape/ appearance/ etc, When time passes.
 * @version 1.0 17 April 2018
 * @author Avi miletzky
 */
public class SpriteCollection {

    private List<Sprite> spritesList = new ArrayList<>();

    /**
     * This method adds the new sprite to the spriteList.
     * @param s - the given sprite.
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * This method removes the given sprite from the spriteList.
     * @param s - the given sprite.
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }

    /**
     * This method calls timePassed() on all sprites.
     * @param dt -
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).timePassed(dt);
        }
    }

    /**
     * This method calls drawOn(d) on all sprites.
     * @param d - the given DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).drawOn(d);
        }
    }
}