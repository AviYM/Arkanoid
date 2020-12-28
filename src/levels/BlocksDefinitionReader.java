package levels;

import objects.BlockCreator;
import objects.BlockDefinitions;
import objects.DefaultPropertiesOfBlocks;
import objects.SpacersDefinitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class BlocksDefinitionReader {

    /**
     * This method reads the block definitions file.
     * @param reader - the reader file.
     * @return BlocksFromSymbolsFactory - the block factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        BufferedReader buffer = new BufferedReader(reader);
        DefaultPropertiesOfBlocks dpob = null;
        BlockDefinitions bd;
        try {
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                if (line.startsWith("default")) {
                    dpob = new DefaultPropertiesOfBlocks();
                    dpob.definesDefaults(line);
                } else if (line.startsWith("bdef")) {
                    bd = new BlockDefinitions(dpob);
                    bd.setBlockDefinitions(line);
                    String symbol = bd.getKey();
                    BlockCreator bc = bd.getBlockCreator();
                    blockCreators.put(symbol, bc);
                } else if (line.startsWith("sdef")) {
                    SpacersDefinitions sd = new SpacersDefinitions();
                    sd.setSpacerWidths(line);
                    String symbol = sd.getSymbol();
                    int width = sd.getWidth();
                    spacerWidths.put(symbol, width);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}