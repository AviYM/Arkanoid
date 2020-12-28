package game;

import biuoop.DrawSurface;
import collection.Animation;
import collection.SpriteCollection;
import java.awt.Color;

/**
 * This class defines interface of animations which occur every time they are called a single action.
 * @version 1.0 23 Mars 2018
 * @author Avi miletzky
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private int countdown;
    private SpriteCollection gameScreen;
    private Boolean stop;
    private double initialTime = System.currentTimeMillis();

    /**
     * This method uses as a constructor.
     * @param numOfSeconds - Total time of the animation display.
     * @param countFrom - The number that countdown starts from.
     * @param gameScreen - Required to display the CountdownAnimation on the specific given gameScreen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.countdown = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * This method draws the CountdownAnimation on a given DrawSurface at each time it is called.
     * @param  d - the given DrawSurface.
     * @param  dt -
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (countdown == 1) {
            this.stop = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        String currValue = getValue();
        d.drawText(388, 2 * d.getHeight() / 3, currValue, 45);
    }

    /**
     * This method is used as a stop condition to finish drawing.
     * @return this.stop
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * This method displays a value for a time defined by (numOfSeconds / countFrom).
     * @return the value to draw.
     */
    private String getValue() {
        double currentTime = System.currentTimeMillis();
        if ((currentTime - initialTime) > (numOfSeconds / countFrom) * 1000) {
            countdown--;
            initialTime = System.currentTimeMillis();
        }
        return (Integer.toString(countdown));
    }
}