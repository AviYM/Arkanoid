package game;

import biuoop.DrawSurface;
import collection.Sprite;
import objects.Counter;
import java.awt.Color;

/**
 * This class displays the number of lives that are left on the header of the game window.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class LivesIndicator implements Sprite {

    private Counter lives;

    /**
     * This method uses as a constructor.
     * @param lives - the given lives Counter - required in order to display.
     * the number of lives on the screen.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * This method draw the LivesIndicator to the screen.
     * @param d - the given DrawSurface
     */
    public void drawOn(DrawSurface d) {
        Color color = new Color(0x000000);
        d.setColor(color);
        /*d.drawText(60, 14, "Lives: ", 12);
        for (int i = 0; i < this.lives.getValue(); i++) {
            d.drawText(100 + i * 15, 15, String.valueOf(Character.toChars('\u2764')), 14);
        }*/
        d.drawText(60, 15, "Lives: " + Integer.toString(lives.getValue()), 14);
    }

    /**
     * This method notify the LivesIndicator that time has passed.
     * @param  dt -
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * This method adds THIS LivesIndicator to the given game.
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
