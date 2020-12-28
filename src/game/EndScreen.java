package game;

import biuoop.DrawSurface;
import collection.Animation;
import java.awt.Color;

/**
 * This class defines the end screen.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class EndScreen implements Animation {

    private int score;
    private int numOflives;

    /**
     * This method uses as a constructor.
     * @param score - required in order to print the score in end screen.
     * @param livesNum - Required to identify the situation where the game is over.
     */
    public EndScreen(int score, int livesNum) {
        this.score = score;
        this.numOflives = livesNum;
    }

    /**
     * This method draws the EndScreen on a given DrawSurface at each time it is called.
     * @param d - the given DrawSurface.
     * @param dt -
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xBEBEBE));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        if (numOflives == 0) {
            d.setColor(Color.WHITE);
            d.drawText(280, 120, "You Lost", 60);
            //d.drawText(240, 120, "Game Over", 60);
            d.drawText(300, 520, "Your score is: " + score, 28);
            d.setColor(Color.YELLOW);
            d.fillCircle(400, 310, 128);
            d.setColor(Color.BLACK);
            int emoji = 128546;
            d.drawText(250, 420, String.valueOf(Character.toChars(emoji)), 300);
            d.drawText(281, 122, "You Lost", 60);
            d.drawText(301, 522, "Your score is: " + score, 28);
        } else {
            d.setColor(Color.WHITE);
            d.drawText(280, 120, "You Win!", 60);
            d.drawText(300, 520, "Your score is: " + score, 28);
            d.setColor(Color.YELLOW);
            d.fillCircle(400, 310, 128);
            d.setColor(Color.BLACK);
            int emoji = 128515; //129303;
            d.drawText(250, 420, String.valueOf(Character.toChars(emoji)), 300);
            d.drawText(281, 122, "You Win!", 60);
            d.drawText(301, 522, "Your score is: " + score, 28);
        }
    }

    /**
     * This method is used as a stop condition to finish drawing.
     * @return this.close
     */
    public boolean shouldStop() { return false; }
}
