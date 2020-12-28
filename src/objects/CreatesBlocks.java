package objects;

import java.util.HashMap;
import java.util.Map;

/**
 * This class produces blocks, from its internal block definitions.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class CreatesBlocks implements BlockCreator {

    private Map<Integer, Background> backgroundOfEachHit;
    private Background background;
    private Stroke stroke;
    private Integer numOfHits;
    private Integer width;
    private Integer height;
    private boolean theMapIsComplete;
    private boolean theFieldsAreFill;

    /**
     * This method creates block, and reset its parameters.
     */
    public CreatesBlocks() {
        this.backgroundOfEachHit = null;
        this.background = null;
        this.stroke = null;
        this.numOfHits = null;
        this.width = null;
        this.height = null;
        this.theMapIsComplete = false;
        this.theFieldsAreFill = false;
    }

    /**
     * This method accepts background and save it in this.background.
     * @param bg - the given Background.
     */
    public void setBackground(Background bg) {
        this.background = bg;
    }

    /**
     * This method accepts Stroke and save it in this.stroke.
     * @param s - the given Stroke.
     */
    public void setStroke(Stroke s) {
        this.stroke = s;
    }

    /**
     * This method accepts Number of hits and save it in this.numOfHits.
     * @param num - the given Number of hits.
     */
    public void setNumOfHits(Integer num) {
        this.numOfHits = num;
    }

    /**
     * This method accepts width of block and save it in this.width.
     * @param newWidth - the given width.
     */
    public void setWidth(Integer newWidth) {
        this.width = newWidth;
    }

    /**
     * This method accepts height of block and save it in this.height.
     * @param newHeight - the given height.
     */
    public void setHeight(Integer newHeight) {
        this.height = newHeight;
    }

    /**
     * This method accepts map of Background Of Each Hit and save it in this.backgroundOfEachHit.
     * @param m - the given map of Background Of Each Hit.
     */
    public void setBackgroundOfEachHit(Map<Integer, Background> m) {
        this.backgroundOfEachHit = m;
    }

    /**
     * This method adds Background Of Hit to the this.backgroundOfEachHit.
     * @param key - the given key.
     * @param newBackground - the given Background-value.
     */
    public void addBackgroundOfHit(int key, Background newBackground) {
        if (this.backgroundOfEachHit == null) {
            this.backgroundOfEachHit = new HashMap<>();
        }
        if (this.backgroundOfEachHit.containsKey(key)) {
            this.backgroundOfEachHit.replace(key, newBackground);
        } else {
            this.backgroundOfEachHit.put(key, newBackground);
        }
    }

    /**
     * This method returns true if all the information needed to create the blocks exists.
     */
    private void allFieldsAreFill() {
        if (((this.numOfHits != null && this.backgroundOfEachHit.containsKey(1))
                || (this.numOfHits == null && this.background != null))
                && this.width != null && this.height != null) {
            this.theFieldsAreFill = true;
        }
    }

    /**
     * This method completes the Map by the given default definitions.
     */
    private void completesMap() {
        if (numOfHits != null && this.backgroundOfEachHit == null) {
            this.backgroundOfEachHit = new HashMap<>();
        }
        for (int i = this.numOfHits; i > 0; i--) {
            if (!this.backgroundOfEachHit.containsKey(i)) {
                if (this.background != null) {
                    addBackgroundOfHit(i, this.background);
                } else if (this.backgroundOfEachHit.containsKey(1)) {
                    addBackgroundOfHit(i, this.backgroundOfEachHit.get(1));
                } else {
                    throw new RuntimeException("Missing background settings are needed");
                }
            }
        }
        this.theMapIsComplete = true;
    }

    @Override
    public Block create(int xpos, int ypos) {
        if (!this.theMapIsComplete) {
            completesMap();
        }
        if (!this.theFieldsAreFill) {
            allFieldsAreFill();
        }
        Block b = new Block(xpos, ypos, this.width, this.height);
        b.setBackgroundOfEachHit(this.backgroundOfEachHit);
        b.setBackground(this.background);
        b.setStroke(this.stroke);
        b.setNumOfHits(this.numOfHits);
        return b;
    }
}