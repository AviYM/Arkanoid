package levels;

import collection.Sprite;
import geometry.Velocity;
import objects.Block;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads a specific level file.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class LevelSpecificationReader {

    private List<LevelInformation> levelsList = new ArrayList<>();
    private BlocksFromSymbolsFactory bfsf;
    private int x;
    private int y;

    /**
     * This method reads a specific level file.
     * @param reader - the given file reader.
     * @return list of levels.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader buffer = new BufferedReader(reader);
        try {
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                if (line.equals("START_LEVEL")) {
                    readLevel(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.levelsList;
    }

    /**
     * This method reads a specific level file in a specific game.
     * @param buffer - the given buffer reader.
     */
    private void readLevel(BufferedReader buffer) {
        Integer blocksStartX = null;
        Integer blocksStartY = null;
        Integer rowHeight = null;
        Level level;
        try {
            level = new Level();
            String line = buffer.readLine();
            while (!line.startsWith("START_BLOCKS")) {
                if (line.isEmpty() || line.startsWith("#")) {
                    line = buffer.readLine();
                    continue;
                }
                String[] dataLine = line.split(":");
                switch (dataLine[0]) {
                    case "level_name": level.setLevelName(dataLine[1]); break;
                    case "ball_velocities": level.setBallVelocities(setVelocities(dataLine[1])); break;
                    case "background": level.setBackground(setBackground(dataLine[1])); break;
                    case "paddle_speed": level.setPaddleSpeed(Integer.parseInt(dataLine[1])); break;
                    case "paddle_width": level.setPaddleWidth(Integer.parseInt(dataLine[1])); break;
                    case "block_definitions": callToBlocksDefinitionReader(dataLine[1]); break;
                    case "blocks_start_x": blocksStartX = Integer.parseInt(dataLine[1]); break;
                    case "blocks_start_y": blocksStartY = Integer.parseInt(dataLine[1]); break;
                    case "row_height": rowHeight = Integer.parseInt(dataLine[1]); break;
                    case "num_blocks": level.setNumOfBlocks(Integer.parseInt(dataLine[1])); break;
                    default: break;
                }
                line = buffer.readLine();
            }
            if (blocksStartX == null || blocksStartY == null || rowHeight == null) {
                throw new RuntimeException("Missing information needed to create blocks");
            }
            List<Block> blocks = new ArrayList<>();
            this.y = blocksStartY;
            line = buffer.readLine();
            while (!line.startsWith("END_BLOCKS")) {
                if (line.isEmpty() || line.startsWith("#")) {
                    line = buffer.readLine();
                    continue;
                }
                this.x = blocksStartX;
                char[] data = line.toCharArray();
                for (int i = 0; i < data.length; i++) {
                    if (this.bfsf.isBlockSymbol(data[i] + "")) {
                        Block b = this.bfsf.getBlock(data[i] + "", this.x, this.y);
                        blocks.add(b);
                        this.x += b.getWidth();
                    } else if (this.bfsf.isSpaceSymbol(data[i] + "")) {
                        this.x += this.bfsf.getSpaceWidth(data[i] + "");
                    }
                }
                this.y += rowHeight;
                line = buffer.readLine();
            }
            level.setBlocks(blocks);
            this.levelsList.add(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the background of level.
     * @param s - the given string with the background.
     * @return background as a sprite.
     */
    private Sprite setBackground(String s) {
        SetBackground setBackground = new SetBackground();
        return setBackground.getBackgroundAsSprite(s);
    }

    /**
     * This method sets the velocities of level.
     * @param velo - the given string with the velocities.
     * @return list of ball velocities of level
     */
    private List<Velocity> setVelocities(String velo) {
        String[] veloArr = velo.split(" ");
        List<Velocity> velocities = new ArrayList<>();
        for (String s : veloArr) {
            String[] splitAngelAndSpeed = s.split(",");
            Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(splitAngelAndSpeed[0]),
                    Double.parseDouble(splitAngelAndSpeed[1]));
            velocities.add(v);
        }
        return velocities;
    }

    /**
     * This method open the blocks Definitions file.
     * @param s - the link to blocks Definitions file.
     * @exception IOException if open file is filed.
     */
    private void callToBlocksDefinitionReader(String s) throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
        Reader reader = new InputStreamReader(is);
        this.bfsf = BlocksDefinitionReader.fromReader(reader);
    }
}