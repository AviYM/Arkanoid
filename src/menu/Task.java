package menu;

/**
 * This interface defines a generic task, that AnimationRunner can run.
 * @param <T> - the given task
 */
public interface Task<T> {
    /**
     * This interface defines a generic task, that AnimationRunner can run.
     * @return - specific type object.
     */
    T run();
}