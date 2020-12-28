package objects;

/**
 * This class reads the Spacers type definitions from the block definitions file.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class SpacersDefinitions {

    private String symbol;
    private int width;

    /**
     * This method accepts a space definition string from the block definition file.
     * Reads the data, and saves the symbol and its value.
     * @param data - the given data.
     */
    public void setSpacerWidths(String data) {
        String[] dataSplit = data.split(" ");
        String[] oneDetail = dataSplit[1].split(":");
        this.symbol = oneDetail[1];
        oneDetail = dataSplit[2].split(":");
        this.width = Integer.parseInt(oneDetail[1]);
    }

    /**
     * This method returns the symbol.
     * @return  this.symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * This method returns the value-width of the symbol.
     * @return  this.width
     */
    public int getWidth() {
        return this.width;
    }
}