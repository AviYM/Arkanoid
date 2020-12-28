package objects;

/**
 * This class creates a counter that can be enlarged and reduced, and get its current value.
 * @version 1.0 17 Mars 2018
 * @author Avi miletzky
 */
public class Counter {

    private int counter;

    /**
     * This method uses as a constructor. which gets a number(count) and keeps it in the counter.
     * @param count - the given count
     */
    public Counter(int count) {
        this.counter = count;
    }

    /**
     * This method adds number to current count.
     * @param number - the given number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * This method subtracts number from current count.
     * @param number - the given number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * This method returns the current value of the counter.
     * @return this.counter
     */
    public int getValue() {
        return this.counter;
    }
}
