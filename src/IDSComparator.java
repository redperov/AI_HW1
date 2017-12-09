import java.util.*;

/**
 * The interface is used to compare between to IDS states.
 * It decision is made according to the creation time and the direction the state came from.
 */
public class IDSComparator implements Comparator<State<Position>> {

    @Override
    /**
     * Compares between two position states.
     */
    public int compare(State<Position> s1, State<Position> s2) {

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
            } else { //TODO is that ok that there might be two nodes with the same priority?
                return 0;
            }
        }
    }
}
