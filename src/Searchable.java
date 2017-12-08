import java.util.List;

/**
 * The interface represents searchable object.
 * Provides all the necessary functionality to use a searchable object.
 */
public interface Searchable<T> {

    /**
     * Returns the initial state.
     * @return state
     */
    State<T> getInitialState();

    /**
     * Returns the goal state.
     * @return state
     */
    State<T> getGoalState();

    /**
     * Adds a value in the structure.
     * @param value value
     */
    void addValue(String value);

    /**
     * Returns a list of all the possible states from a given state.
     * @param state state
     * @return list of states
     */
    List<State<T>> getAllPossibleStates(State<T> state);
}
