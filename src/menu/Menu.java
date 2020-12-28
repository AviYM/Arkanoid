package menu;

import collection.Animation;

/**
 * This interface defines menu.
 * @version 1.0 17 june 2018
 * @author Avi miletzky
 * @param <T> generic type.
 */
public interface Menu<T> extends Animation {

    /**
     * This method adds option to the menu.
     * @param key - Select key.
     * @param message - Option description.
     * @param returnVal - the corresponding Task.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * This method returns the selected task.
     * @return T - task to run.
     */
    T getStatus();
    /**
     * This method adds option to the menu.
     * @param key - Select key.
     * @param message - Option description.
     * @param subMenu - the corresponding subMenu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
