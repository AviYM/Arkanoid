package objects;

/**
 * This interface defines type which holds information that allows it to create blocks of its kind.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public interface BlockCreator {

    /**
     * This method create a block at the specified location.
     * @param xpos - Position on the X axis
     * @param ypos - Position on the Y axis
     * @return new Block.
     */
    Block create(int xpos, int ypos);
}
