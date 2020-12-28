package game;

import table.HighScoresAnimation;
import table.HighScoresTable;
import table.ScoreInfo;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import collection.Animation;
import collection.KeyPressStoppableAnimation;
import levels.LevelInformation;
import objects.Counter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class holds all levels of the game, and sends each time one level to the GameLevel that runs it.
 * @version 1.0 17 April 2018
 * @author Avi miletzky
 */
public class GameFlow {

    private Counter numOfLives;
    private int initialLivesNumber;
    private Counter score;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private File highScores;
    private HighScoresTable table;
    private DialogManager dialog;

    /**
     * This method uses as a constructor.
     * @param ar - the given AnimationRunner that actually running the game loop.
     * @param ks - the given keyboardSensor -required to send to several animation.
     * @param highScores - the file to read from.
     * @param table - HighScoresTable to show.
     * @param dialog - to save the name of winner player.
     * @param numLives - the given number of lives of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, File highScores, HighScoresTable table,
                    DialogManager dialog, int numLives) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.highScores = highScores;
        this.table = table;
        this.dialog = dialog;
        this.numOfLives = new Counter(numLives);
        this.initialLivesNumber = numLives;
        this.score = new Counter(0);
    }

    /**
     * This method reset the numOfLives & score at the beginning of each game.
     */
    public void reset() {
        this.numOfLives = new Counter(this.initialLivesNumber);
        this.score = new Counter(0);
    }

    /**
     * This method returns this score.
     * @return  - the score.
     */
    public int getScore() {
        return this.score.getValue();
    }

    /**
     * This method runs all levels in the received list.
     * @param levels - the given list of LevelInformation.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.score, this.numOfLives);
            level.initialize();
            while (level.getBlocksCounter().getValue() > 0 && this.numOfLives.getValue() > 0) {
                level.playOneTurn();
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(this.score.getValue(),
                this.numOfLives.getValue())));
        int i = table.getRank(this.score.getValue());
        if (i < table.size()) {
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            table.add(new ScoreInfo(name, this.score.getValue()));
            try {
                table.save(highScores);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Animation hSa = new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(table));
        animationRunner.run(hSa);
    }
}