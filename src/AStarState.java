/**
 * Created by Danny on 09/12/2017.
 */
public class AStarState<T> extends State<T> {

    //TODO delete this class if not needed

    private int g;
    private int h;
    private int f;

    /**
     * Constructor.
     * @param state state
     * @param g g value
     * @param h h value
     */
    public AStarState(T state, int g, int h) {
        super(state);
        this.g = g;
        this.h = h;
        this.f = this.g + this.h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
}
