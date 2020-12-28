package game;

import biuoop.DrawSurface;
import collection.Sprite;
import java.awt.Color;

/**
 * This class displays the score on the header of the game window.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class NameLevelIndicator implements Sprite {

    private String nameOfLevel;

    /**
     * This method uses as a constructor.
     * @param name - the given level's name - required in order to display the name of level on the screen.
     */
    public NameLevelIndicator(String name) {
        this.nameOfLevel = name;
    }

    /**
     * This method draw the NameLevelIndicator to the screen.
     * @param d - the given DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(580, 15, "Level Name: " + this.nameOfLevel, 12);
    }

    /**
     * This method notify the NameLevelIndicator that time has passed.
     * @param dt -
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * This method adds THIS NameLevelIndicator to the given game.
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}