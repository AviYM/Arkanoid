package levels;

import java.awt.Color;

/**
 * This class.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class ColorsParser {
    /**
     * This method parse color definition and return the specified color.
     * @param s - color as a string.
     * @return color.
     */
    public Color colorFromString(String s) {
        String[] splitStr = s.split("[()]");
        if (splitStr[1].startsWith("RGB")) {
            splitStr = splitStr[2].split(",");
            int[] colorNum = new int[3];
            for (int i = 0; i < splitStr.length; i++) {
                colorNum[i] = Integer.parseInt(splitStr[i]);
            }
            return new Color(colorNum[0], colorNum[1], colorNum[2]);
        } else {
            switch (splitStr[1]) {
                case "black": case "BLACK": return Color.BLACK;
                case "blue": case "BLUE": return Color.BLUE;
                case "cyan": case "CYAN": return Color.CYAN;
                case "darkGray": case "DARK_GRAY": return Color.DARK_GRAY;
                case "gray": case "GRAY": return Color.GRAY;
                case "green": case "GREEN": return Color.GREEN;
                case "lightGray": case "LIGHT_GRAY": return Color.LIGHT_GRAY;
                case "magenta": case "MAGENTA": return Color.MAGENTA;
                case "orange": case "ORANGE": return Color.ORANGE;
                case "pink": case "PINK": return Color.PINK;
                case "red": case "RED": return Color.RED;
                case "white": case "WHITE": return Color.WHITE;
                case "yellow": case "YELLOW": return Color.YELLOW;
                default: return null;
            }
        }
    }
}
