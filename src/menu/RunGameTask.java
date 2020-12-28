package menu;

import game.GameFlow;
import levels.LevelInformation;
import java.util.ArrayList;
import java.util.List;

/**
 * This class runs the specific level in the game.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class RunGameTask implements Task<Void> {

    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * This method runs the specific level in the game.
     * @param gF - GameFlow gF in order to runs.
     * @param levels - the list of levels.
     */
    public RunGameTask(GameFlow gF, List<LevelInformation> levels) {
        this.gameFlow = gF;
        this.levels = levels;
    }
    @Override
    public Void run() {
        List<LevelInformation> levelsToRun = new ArrayList<>(this.levels);
        this.gameFlow.runLevels(levelsToRun);
        this.gameFlow.reset();
        return null;
    }
}