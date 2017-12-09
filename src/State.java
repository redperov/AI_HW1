/**
 * The class represents a state of a problem.
 * It is used to store objects which are states of specific problems.
 */
public class State<T> {

    //The actual state.
    private T     state;

    //Counter used to set the creation time for all the states.
    private static int timer = 0;

    //State creation time.
    private  int creationTime;

    //The previous state.
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
     * Increases the timer.
     */
    public static void increaseTimer(){
        timer++;
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
    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
        this.creationTime = timer;
    }

    @Override
    /**
     * Overrides the equals method to compare between two states according to their X and Y coordinates.
     */
    public boolean equals(Object obj) {

        return this.getState().equals(((State)obj).getState());
    }
}
