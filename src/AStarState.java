/**
 * The class represents an A* state.
 * It extends the State class, and in addition holds the g(n), h(n) and f(n) values used in A*.
 */
public class AStarState<T> extends State<T> {

    //Value of g(n).
    private int g;

    //Value of h(n).
    private int h;

    //Value if f(n).
    private int f;

    /**
     * Constructor.
     *
     * @param state state
     * @param g     g value
     * @param h     h value
     */
    public AStarState(T state, int g, int h) {

        super(state);
        this.g = g;
        this.h = h;
        this.f = this.g + this.h;
    }

    /**
     * g(n) getter.
     *
     * @return g(n)
     */
    public int getG() {
        return g;
    }

    /**
     * g(n) setter.
     *
     * @param g g(n)
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * h(n) getter.
     *
     * @return h(n)
     */
    public int getH() {
        return h;
    }

    /**
     * h(n) setter.
     *
     * @param h h(n)
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * f(n) getter.
     *
     * @return f(n)
     */
    public int getF() {
        return f;
    }

    /**
     * f(n) setter.
     *
     * @param f f(n)
     */
    public void setF(int f) {
        this.f = f;
    }
}
