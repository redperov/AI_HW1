/**
 * The interface represents a comparator.
 * It is used to compare between objects.
 */
public interface Comparator<T> {

    /**
     * Compares between two given states.
     * @param s1 state 1
     * @param s2 state 2
     * @return 0 - equal, 1 - s1 is chosen, 2 - s2 is chosen
     */
    int compare(State<T> s1, State<T> s2);
}
