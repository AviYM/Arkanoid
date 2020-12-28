package table;

import biuoop.DrawSurface;
import collection.Animation;
import java.awt.Color;

/**
 * This class displays the high-score table.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable hSt;

    /**
     * This method uses as a constructor of HighScoresAnimation.
     * @param scores - the given high-score table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.hSt = scores;
    }
    /**
     * This method draws animation on a given DrawSurface at each time it is called.
     * @param  d - the given DrawSurface.
     * @param dt -
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x434446));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(60, 100, "High Scores", 50);
        d.setColor(Color.WHITE);
        d.drawText(61, 101, "High Scores", 50);
        d.setColor(Color.BLACK);
        d.drawText(100, 170, "Player Name", 30);
        d.setColor(Color.WHITE);
        d.drawText(101, 171, "Player Name", 30);
        d.setColor(Color.BLACK);
        d.drawText(500, 170, "Score", 30);
        d.setColor(Color.WHITE);
        d.drawText(501, 171, "Score", 30);
        for (int i = 0; i < this.hSt.getHighScores().size(); i++) {
            d.setColor(Color.WHITE);
            d.drawText(100, 220 + i * 30, this.hSt.getHighScores().get(i).getName(), 20);
            d.drawText(500, 220 + i * 30, "" + this.hSt.getHighScores().get(i).getScore(), 20);
        }
        d.setColor(Color.pink);
        d.fillRectangle(100, 180, 600, 5);
        d.setColor(Color.BLACK);
        d.drawText(195, 500, "press space to continue", 40);
        d.setColor(Color.WHITE);
        d.drawText(196, 502, "press space to continue", 40);
    }

    /**
     * This method is used as a stop condition to finish drawing.
     * @return the current condition.
     */
    public boolean shouldStop() {
        return false;
    }
}
