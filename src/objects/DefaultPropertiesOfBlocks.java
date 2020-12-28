package objects;

import levels.ColorsParser;
import levels.SetBackground;

import java.util.HashMap;
import java.util.Map;

/**
 * This class reads the default definitions of block definitions.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class DefaultPropertiesOfBlocks {

    private Integer defaultNumOfHits;
    private Integer defaultBlockWidth;
    private Integer defaultBlockHeight;
    private Background defaultBg;
    private Map<Integer, Background> defaultBackgroundOfEachHit;
    private Stroke defaultStroke;

    /**
     * This method used as a constructor and reset the parameters of this object.
     */
    public DefaultPropertiesOfBlocks() {
        this.defaultNumOfHits = null;
        this.defaultBlockWidth = null;
        this.defaultBlockHeight = null;
        this.defaultBg = null;
        this.defaultBackgroundOfEachHit = null;
        this.defaultStroke = null;
    }

    /**
     * This method returns the default Number Of Hits.
     * @return this.defaultNumOfHits
     */
    public Integer getDefaultNumOfHits() {
        return this.defaultNumOfHits;
    }

    /**
     * This method returns the default Block Width.
     * @return this.defaultBlockWidth
     */
    public Integer getDefaultBlockWidth() {
        return this.defaultBlockWidth;
    }

    /**
     * This method returns the default Block Height.
     * @return this.defaultBlockHeight
     */
    public Integer getDefaultBlockHeight() {
        return this.defaultBlockHeight;
    }

    /**
     * This method returns the default Background.
     * @return this.defaultBg
     */
    public Background getDefaultBg() {
        return this.defaultBg;
    }

    /**
     * This method returns the default background Of each hit.
     * @return this.defaultBackgroundOfEachHit
     */
    public Map<Integer, Background> getDefaultBackgroundOfEachHit() {
        return this.defaultBackgroundOfEachHit;
    }

    /**
     * This method returns the default Stroke.
     * @return this.defaultStroke
     */
    public Stroke getDefaultStroke() {
        return this.defaultStroke;
    }

    /**
     * This method reads the given line and saves the data.
     * @param line - the given line.
     */
    public void definesDefaults(String line) {
        String[] data = line.split(" ");
        for (String s : data) {
            String[] oneDetail = s.split(":");
            if (oneDetail[0].startsWith("fill-")) {
                addToMap(oneDetail);
            }
            switch (oneDetail[0]) {
                case "hit_points" : this.defaultNumOfHits = Integer.parseInt(oneDetail[1]); break;
                case "width" : this.defaultBlockWidth = Integer.parseInt(oneDetail[1]); break;
                case "height" : this.defaultBlockHeight = Integer.parseInt(oneDetail[1]); break;
                case "stroke" : this.defaultStroke = new Stroke(
                        new ColorsParser().colorFromString(oneDetail[1])); break;
                case "fill" : this.defaultBg = setBackground(oneDetail[1]); break;
                default: break;
            }
        }
    }

    /**
     * This method adds a new background to the this.defaultBackgroundOfEachHit map.
     * @param s - the given data.
     */
    private void addToMap(String[] s) {
        String[] numHit = s[0].split("-");
        if (this.defaultBackgroundOfEachHit == null) {
            this.defaultBackgroundOfEachHit = new HashMap<>();
        }
        this.defaultBackgroundOfEachHit.put(Integer.parseInt(numHit[1]),
                setBackground(s[1]));
    }

    /**
     * This method creates a new background from the given string.
     * @param s - the given string.
     * @return new Background.
     */
    private Background setBackground(String s) {
        SetBackground background = new SetBackground();
        return background.getBackground(s);
    }
}
