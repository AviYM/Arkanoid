package menu;

import collection.KeyPressStoppableAnimation;
import game.AnimationRunner;

/**
 * This class display the HiScoresTable.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private KeyPressStoppableAnimation animation;

    /**
     * This method display the HiScoresTable.
     * @param runner - AnimationRunner to runs the table.
     * @param animation - the table.
     */
    public ShowHiScoresTask(AnimationRunner runner, KeyPressStoppableAnimation animation) {
        this.runner = runner;
        this.animation = animation;
    }
    /**
     * This method display the HiScoresTable.
     * @return Void
     */
    public Void run() {
        this.runner.run(this.animation);
        return null;
    }
}