package collection;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * This method uses as a constructor.
     * @param sensor -
     * @param key -
     * @param animation -
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {

        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * This method draws animation on a given DrawSurface at each time it is called.
     * @param  d - the given DrawSurface.
     * @param dt -
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * This method is used as a stop condition to finish drawing.
     * @return the current condition.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
