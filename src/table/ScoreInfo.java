package table;

/**
 * This class defines player with name and score.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class ScoreInfo {

    private String name;
    private int score;

    /**
     * This class defines player with name and score.
     * @param name - the name of player.
     * @param score - the score of player.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * This method returns the name of player.
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the score of player.
     * @return this.score
     */
    public int getScore() {
        return this.score;
    }
}