package menu;

import game.AnimationRunner;

/**
 * This class runs the specific subMenu.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class RunSubMenu implements Task<Void> {

    private AnimationRunner ar;
    private Menu<Task<Void>> menu;

    /**
     * This method runs the specific subMenu.
     * @param ar - AnimationRunner to runs the sub menu.
     * @param menu - the specific subMenu.
     */
    public RunSubMenu(AnimationRunner ar, Menu<Task<Void>> menu) {
        this.ar = ar;
        this.menu = menu;
    }
    @Override
    public Void run() {
        this.ar.run(this.menu);
        // wait for user selection
        Task<Void> task = this.menu.getStatus();
        task.run();
        return null;
    }
}
