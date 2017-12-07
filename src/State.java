/**
 * The class represents a state of a problem.
 * It is used to store objects which are states of specific problems.
 */
public class State<T> {

    private T     state;
    private State cameFrom;

    /**
     * Constructor.
     * @param state Generic state.
     */
    public State(T state) {
       this.state = state;
    }

    /**
     * State getter.
     * @return state
     */
    public T getState() {
        return state;
    }

    /**
     * Parent state setter.
     * @return parent state
     */
    public State getCameFrom() {
        return cameFrom;
    }

    /**
     * Parent state setter.
     * @param cameFrom parent state
     */
    public void setCameFrom(State cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    /**
     * Overrides the equals method, to compare between two states according to their X and Y coordinates.
     */
    public boolean equals(Object obj) {

        return this.getState().equals(((State)obj).getState());
    }
}
