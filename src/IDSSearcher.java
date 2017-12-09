import java.util.*;

/**
 * The class implements the IDS algorithm.
 * It uses the DFS algorithm with Duplicate Pruning.
 */
public class IDSSearcher implements Searcher<Position> {

    //List used for Duplicate Pruning.
    private ArrayList<State<Position>> duplicatePruning;

    //The board that is used for searching.
    private Searchable<Position> searchable;

    //IDS comparator.
    private Comparator<State<Position>> comparator;

    //Algorithm bound.
    private int bound;

    /**
     * Constructor
     *
     * @param bound algorithm bound
     */
    public IDSSearcher(int bound) { //TODO maybe make bound to be provided by the searchable

        this.bound = bound;
        this.comparator = new IDSComparator();
    }

    @Override
    /**
     * Finds a solution using IDS.
     */
    public Solution search(Searchable<Position> searchable) {

        this.searchable = searchable;
        this.duplicatePruning = new ArrayList<>();

        //Get the initial state.
        State<Position> root = this.searchable.getInitialState();
        this.duplicatePruning.add(root);

        //Run the IDS search.
        State<Position> node = IDS(root);

        return new Solution(node);
    }

    /**
     * Performs and IDS search.
     *
     * @param root root node
     * @return goal state
     */
    private State<Position> IDS(State<Position> root) {

        State<Position> found;

        //Increase depth for each unsuccessful DLS search up to a given bound.
        for (int depth = 0; depth < this.bound; depth++) {

            //Run DFS with limited depth.
            found = DLS(root, depth);

            if (found != null) {
                return found;
            }
        }

        return null;
    }

    /**
     * Performs a limited depth DFS search.
     *
     * @param node  node
     * @param depth depth
     * @return node
     */
    private State<Position> DLS(State<Position> node, int depth) {

        //Check if goal state was found.
        if (depth == 0 && node.equals(searchable.getGoalState())) {

            return node;
        }

        if (depth > 0) {

            //Get all the node's neighbours.
            //TODO this might not work, because in each iteration I forget the neighbours, and maybe should use a
            // stack instead
            List<State<Position>> neighbours = this.searchable.getAllPossibleStates(node);

            //Sort the nodes according to the IDS comparator.
            neighbours.sort(this.comparator);

            //Run over all the neighbours.
            for (State<Position> neighbour : neighbours) {

                //Check if neighbour exists in the Duplicate Pruning.
                if (!this.duplicatePruning.contains(neighbour)) {

                    //Add neighbour to Duplicate Pruning.
                    this.duplicatePruning.add(neighbour);

                    //Set where the neighbour came from.
                    neighbour.setCameFrom(node);

                    //Call DLS on neighbour.
                    State<Position> found = DLS(neighbour, depth - 1);

                    //Remove neighbour from Duplicate Pruning.
                    this.duplicatePruning.remove(neighbour);

                    if (found != null) {
                        return found;
                    }
                }
            }
        }

        return null;
    }
}
