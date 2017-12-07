import java.util.ArrayList;
import java.util.List;

/**
 * The class implements the IDSSearcher algorithm.
 * It uses the DFS algorithm with Duplicate Pruning.
 */
public class IDSSearcher implements Searcher<Position> {

    //List used for Duplicate Pruning.
    private ArrayList<State<Position>> duplicatePruning;
    Searchable<Position> searchable;

    //Algorithm bound.
    private int bound;

    /**
     * Constructor
     * @param bound algorithm bound
     */
    public IDSSearcher(int bound) { //TODO maybe make bound to be provided by the searchable

        this.bound = bound;
    }

    @Override
    /**
     * Performs an IDSSearcher search.
     */
    public Solution search(Searchable<Position> searchable) {

        this.searchable = searchable;
        this.duplicatePruning = new ArrayList<>();

        //Get the initial state.
        State<Position> root = this.searchable.getInitialState();
        this.duplicatePruning.add(root);

        //Run the IDS search.
        State<Position> node = IDS(root);

        //TODO generate solution

        return null;
    }

    private State<Position> IDS(State<Position> root){

        State<Position> found;

        //Increase depth for each unsuccessful DLS search.
        for(int depth = 0; depth < this.bound; depth++){

            found = DLS(root, depth);

            if(found != null){
                return found;
            }
        }

        return null;
    }

    private State<Position> DLS(State<Position> node, int depth){

        //Check if goal state was found.
        if(depth == 0 && node.equals(searchable.getGoalState())){

            return node;
        }

        if(depth > 0){

            //Get all the node's neighbours.
            List<State<Position>> neighbours = this.searchable.getAllPossibleStates(node);

            //Run over all the neighbours.
            for(State<Position> neighbour : neighbours){

                //Check if neighbour exists in the Duplicate Pruning.
                if(!this.duplicatePruning.contains(neighbour)){

                    //Add neighbour to Duplicate Pruning.
                    this.duplicatePruning.add(neighbour);

                    //Call DLS on neighbour.
                    State<Position> found = DLS(neighbour, depth - 1);

                    //Remove neighbour from Duplicate Pruning.
                    this.duplicatePruning.remove(neighbour);

                    if(found != null){
                        return found;
                    }
                }
            }
        }

        return null;
    }
}
