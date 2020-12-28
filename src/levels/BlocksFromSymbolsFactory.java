package levels;

import objects.Block;
import objects.BlockCreator;
import java.util.Map;

/**
 * This class defines a structure that holds everything that is needed to build blocks.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * This method defines a structure that holds everything that is needed to build blocks.
     * @param spacerWidths - map which contain all spacer definitions.
     * @param blockCreators - map which contain all types of blocks.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
                                    Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * This method returns true if 's' is a valid space symbol.
     * @param s - string to check.
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * This method returns true if 's' is a valid block symbol.
     * @param s - string to check.
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * This method return a block according to the definitions associated with symbol s.
     * The block will be located at position (xpos, ypos).
     * @param s - the type of the required block.
     * @param xpos - Position on the X axis.
     * @param ypos - Position on the Y axis.
     * @return a new block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        BlockCreator bc = this.blockCreators.get(s);
        return bc.create(xpos, ypos);
    }
    /**
     * This method returns the width in pixels associated with the given spacer-symbol.
     * @param s - the given string.
     * @return the width in pixels.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
