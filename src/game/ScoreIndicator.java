package game;

import biuoop.DrawSurface;
import collection.Sprite;
import objects.Counter;
import java.awt.Color;

/**
 * This class displays the score of the game on the header of the game window.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class ScoreIndicator implements Sprite {

    private Counter score;

    /**
     * This method uses as a constructor.
     * @param score - the given score Counter - required in order to display the score on the screen.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * This method draw the score on the screen.
     * @param d - the given DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0x000000));
        d.drawText(370, 15, "Score: " + Integer.toString(score.getValue()), 14);
    }

    /**
     * This method notify the ScoreIndicator that time has passed.
     * @param dt -
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * This method adds THIS ScoreIndicator to the given game.
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}