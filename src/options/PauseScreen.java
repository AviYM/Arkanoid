package options;

import biuoop.DrawSurface;
import collection.Animation;
import java.awt.Color;

/**
 * This class defines interface of animations which occur every time they are called a single action.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class PauseScreen implements Animation {

    /**
     * This method draws the PauseScreen on a given DrawSurface at each time it is called.
     * @param  d - the given DrawSurface.
     * @param dt -
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xBEBEBE));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(240, 3 * d.getHeight() / 4,
                "press space to continue", 32);
        d.fillCircle(d.getWidth() / 2, 9 * d.getHeight() / 20, 100);
        d.setColor(new Color(0xBEBEBE));
        d.fillCircle(d.getWidth() / 2, 9 * d.getHeight() / 20, 92);
        d.setColor(Color.BLACK);
        d.fillRectangle(350, 220, 40, 100);
        d.fillRectangle(410, 220, 40, 100);
    }

    /**
     * This method is used as a stop condition to finish drawing.
     * @return this.stop
     */
    public boolean shouldStop() { return false; }
}
