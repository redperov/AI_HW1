/**
 * The class represents a state of a problem.
 * It is used to store objects which are states of specific problems.
 */
public class State<T> {

    private T     state;
    private int creationTime;
    private State<T> cameFrom;

    /**
     * Constructor.
     * @param state Generic state.
     */
    public State(T state) {
       this.state = state;
       this.cameFrom = null;
       this.creationTime = 0;
    }

    /**
     * State getter.
     * @return state
     */
    public T getState() {
        return state;
    }

    /**
     * Creation time getter.
     * @return time
     */
    public int getCreationTime() {
        return creationTime;
    }


    /**
     * Parent state setter.
     * @return parent state
     */
    public State<T> getCameFrom() {
        return cameFrom;
    }

    /**
     * Parent state setter.
     * @param cameFrom parent state
     */
    public void setCameFrom(State cameFrom) {
        this.cameFrom = cameFrom;
        this.creationTime = this.cameFrom.getCreationTime() + 1;
    }

    @Override
    /**
     * Overrides the equals method, to compare between two states according to their X and Y coordinates.
     */
    public boolean equals(Object obj) {

        return this.getState().equals(((State)obj).getState());
    }
}
