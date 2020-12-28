package levels;

import collection.Sprite;
import objects.Background;
import objects.BackgroundColor;
import objects.BackgroundImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class defines background as Sprite or Background.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 */
public class SetBackground {

    private Background background;
    private Sprite sprite;

    /**
     * This method defines background as Sprite or Background.
     * @param bg - the string with a background.
     * @param asSprite - In what way to return the background.
     */
    private void setBackground(String bg, boolean asSprite) {
        String[] splitStr = bg.split("\\(");
        if (bg.startsWith("color")) {
            if (asSprite) {
                this.sprite = new BackgroundColor(new ColorsParser().colorFromString(bg));
            } else {
                this.background = new BackgroundColor(new ColorsParser().colorFromString(bg));
            }
        } else if (bg.startsWith("image")) {
            splitStr = splitStr[1].split("\\)");
            Image image = null;
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(splitStr[0]);
                image = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (asSprite) {
                this.sprite = new BackgroundImage(image);
            } else {
                this.background = new BackgroundImage(image);
            }
        }
    }

    /**
     * This method returns the background as a sprite.
     * @param bg - the string with a background.
     * @return Sprite.
     */
    public Sprite getBackgroundAsSprite(String bg) {
        setBackground(bg, true);
        return this.sprite;
    }

    /**
     * This method returns the background as a Background.
     * @param bg - the string with a background.
     * @return Background.
     */
    public Background getBackground(String bg) {
        setBackground(bg, false);
        return this.background;
    }
}