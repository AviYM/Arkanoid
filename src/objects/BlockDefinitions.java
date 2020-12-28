package objects;

import levels.ColorsParser;
import levels.SetBackground;

/**
 * This class reads the Block definitions from the block definitions file.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class BlockDefinitions {

    private DefaultPropertiesOfBlocks dpob;
    private BlockCreator blockCreator;
    private String key;

    /**
     * This method accept DefaultPropertiesOfBlocks in order to complete the information of block.
     * @param dpob - the default information which has already been read from the file.
     */
    public BlockDefinitions(DefaultPropertiesOfBlocks dpob) {
        this.dpob = dpob;
        this.blockCreator = null;
        this.key = null;
    }

    /**
     * This method accepts a string from the block definition file. Reads the data, and saves them.
     * @param data - the given data.
     */
    public void setBlockDefinitions(String data) {
        CreatesBlocks cb = new CreatesBlocks();
        if (this.dpob != null) {
            cb.setWidth(this.dpob.getDefaultBlockWidth());
            cb.setHeight(this.dpob.getDefaultBlockHeight());
            cb.setNumOfHits(this.dpob.getDefaultNumOfHits());
            cb.setBackgroundOfEachHit(this.dpob.getDefaultBackgroundOfEachHit());
            cb.setBackground(this.dpob.getDefaultBg());
            cb.setStroke(this.dpob.getDefaultStroke());
        }
        String[] dataSplit = data.split(" ");
        for (int i = 1; i < dataSplit.length; i++) {
            String[] s = dataSplit[i].split(":");
            if (s[0].startsWith("fill-")) {
                String[] numHit = s[0].split("-");
                cb.addBackgroundOfHit(Integer.parseInt(numHit[1]), setBackground(s[1]));
            }
            switch (s[0]) {
                case "symbol" : this.key = s[1]; break;
                case "hit_points" : cb.setNumOfHits(Integer.parseInt(s[1])); break;
                case "fill" : cb.setBackground(setBackground(s[1])); break;
                case "width" : cb.setWidth(Integer.parseInt(s[1])); break;
                case "height" : cb.setHeight(Integer.parseInt(s[1])); break;
                case "stroke" : cb.setStroke(new Stroke(
                        new ColorsParser().colorFromString(s[1]))); break;
                default: break;
            }
        }
        this.blockCreator = cb;
    }

    /**
     * This method accept a string ana returns Background.
     * @param s - the given string.
     * @return new background.
     */
    private Background setBackground(String s) {
        SetBackground background = new SetBackground();
        return background.getBackground(s);
    }

    /**
     * This method returns the blockCreator that contain all the information needed to create blocks.
     * @return - this.blockCreator
     */
    public BlockCreator getBlockCreator() {
        return this.blockCreator;
    }

    /**
     * This method returns the key belongs to the blockCreator.
     * @return this.key
     */
    public String getKey() {
        return this.key;
    }
}
