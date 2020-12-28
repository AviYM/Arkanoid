package menu;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Polygon;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 * @param <T> generic type.
 */
public class MenuAnimation<T> implements Menu<T> {

    private HashMap<String, T> selectionTask = new HashMap<>();
    private List<String> menu = new ArrayList<>();
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean isAlreadyPressed;
    private T status = null;
    private String title;

    /**
     * This method runs the menu.
     * @param title - Option description.
     * @param kb - KeyboardSensor to Select key.
     */
    public MenuAnimation(String title, KeyboardSensor kb) {
        this.title = title;
        this.keyboard = kb;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        String s = "(" + key + ")" + " " + message;
        this.menu.add(s);
        this.selectionTask.put(key, returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x4E4F51));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        if (this.title.equals("Arkanoid")) {
            Polygon poly = new Polygon();
            poly.addPoint(76, 122); //left
            poly.addPoint(90, 82); //top
            poly.addPoint(104, 122); //right
            d.setColor(Color.RED);
            d.fillPolygon(poly);
        }
        d.setColor(Color.BLACK);
        d.drawText(70, 120, this.title, 70);
        d.setColor(Color.WHITE);
        d.drawText(71, 122, this.title, 70);
        if (this.title.equals("Arkanoid")) {
            d.setColor(Color.BLACK);
            d.fillCircle(300, 73, 6);
            d.setColor(Color.RED);
            d.fillCircle(301, 74, 6);
        }
        for (int i = 0; i < this.menu.size(); i++) {
            d.setColor(Color.BLACK);
            d.drawText(120, 250 + i * 40, this.menu.get(i), 30);
            d.setColor(Color.WHITE);
            d.drawText(121, 252 + i * 40, this.menu.get(i), 30);
        }
        for (String key: this.selectionTask.keySet()) {
            if (this.keyboard.isPressed(key) && !this.isAlreadyPressed) {
                this.status = selectionTask.get(key);
                this.stop = true;
            }
            if (!this.keyboard.isPressed(key)) {
                this.isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}