import java.util.Comparator;

/**
 * The interface is used to compare between two A* states.
 * It decision is made according to the f(n), creation time and the direction the state came from.
 */
public class AStarComparator implements Comparator<AStarState<Position>> {

    @Override
    /**
     * Compares between two position states.
     */
    public int compare(AStarState<Position> s1, AStarState<Position> s2) {

        //Check which one has the lowest f(n).
        if (s1.getF() < s2.getF()) {
            return -1;
        } else if (s2.getF() < s1.getF()) {
            return 1;
        } else {

            //Check which one has the earliest creation time.
            if (s1.getCreationTime() < s2.getCreationTime()) {
                return -1;
            } else if (s2.getCreationTime() < s1.getCreationTime()) {
                return 1;
            } else {

                //Check which one came from the highest priority direction.
                if (s1.getState().getDirectionCameFrom() < s2.getState().getDirectionCameFrom()) {
                    return -1;
                } else if (s2.getState().getDirectionCameFrom() < s1.getState().getDirectionCameFrom()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
