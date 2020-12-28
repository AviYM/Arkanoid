package levels;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reading the information from the file setLevel.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class SetLevelReader {

    private List<String[]> keys = new ArrayList<>();
    private List<List<LevelInformation>> values = new ArrayList<>();

    /**
     * This method reading the information from the file setLevel.
     * @param reader - the given reader file.
     */
    public void fromReader(java.io.Reader reader) {
        LineNumberReader lineReader = new LineNumberReader(reader);
        try {
            String line;
            while ((line = lineReader.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                if ((lineReader.getLineNumber()) % 2 == 1) {
                    String[] keyAndNameOfLevel = line.split(":");
                    line = lineReader.readLine();
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                    Reader readerLevels = new InputStreamReader(is);
                    LevelSpecificationReader lsr = new LevelSpecificationReader();
                    List<LevelInformation> levels = lsr.fromReader(readerLevels);
                    this.keys.add(keyAndNameOfLevel);
                    this.values.add(levels);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the key of subMenu.
     * @return - the key.
     */
    public List<String[]> getKeys() {
        return this.keys;
    }

    /**
     * This method returns the list of list of levels.
     * @return - the list of list of levels.
     */
    public List<List<LevelInformation>> getValues() {
        return this.values;
    }
}
