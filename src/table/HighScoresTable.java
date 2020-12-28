package table;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * This class.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class HighScoresTable {

    private int size;
    private List<ScoreInfo> highScores;

    /**
     * This method uses as a constructor,
     * and create an empty high-scores table with the specified size.
     * @param size - the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<>();
    }

    /**
     * This method adds a high-score to table.
     * @param score - the data of the new high-score.
     */
    public void add(ScoreInfo score) {
        int i = this.getRank(score.getScore());
        this.highScores.add(i, score);
        //this.highScores.sort(Comparator.comparing(ScoreInfo::getScore));
        while (this.highScores.size() > this.size) {
            this.highScores.remove(this.size);
        }
    }

    /**
     * This method returns the table size.
     * @return this.size
     */
    public int size() {
        return this.size;
    }

    /**
     * This method returns the current high scores.
     * The list is sorted such that the highest scores come first.
     * @return this.highScores
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * This method return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     * @param score - a new score to check.
     * @return the correct rank.
     */
    public int getRank(int score) {
        if (this.highScores.size() == 0) {
            return 0;
        }
        int i = this.highScores.size();
        while (i > 0 && score > this.highScores.get(i - 1).getScore()) {
            i--;
        }
        return i;
    }

    /**
     * This method clears the table.
     */
    public void clear() {
        highScores.clear();
    }

    /**
     * This method load table data from file. current table data is cleared.
     * @param filename - the given file.
     * @exception IOException - if reading or file closure failed.
     */
    public void load(File filename) throws IOException {
        BufferedReader is = null;
        try {
            is = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                String[] nameAndScore = line.split(";");
                ScoreInfo sI = new ScoreInfo(nameAndScore[0], Integer.parseInt(nameAndScore[1]));
                this.highScores.add(sI);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * This method Save table data to the specified file.
     * @param filename - the given file.
     * @exception IOException - if saving or file closure failed.
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(filename)));
            for (ScoreInfo sI : this.highScores) {
                String s = new String(sI.getName() + ";" + sI.getScore() + "\n");
                os.write(s);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * This method read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param filename - the given file.
     * @return the new table it created.
     */
    public static HighScoresTable loadFromFile(File filename) {
        int tableSize = 5;
        HighScoresTable highScoresTable = new HighScoresTable(tableSize);
        try {
            highScoresTable.load(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return highScoresTable;
        }
    }
}
