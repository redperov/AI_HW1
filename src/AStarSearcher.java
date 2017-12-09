import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The class implements the A* algorithm.
 * TODO add more description
 */
public class AStarSearcher implements Searcher<Position> {

    //Open set.
    private PriorityQueue<AStarState<Position>> openList;

    //The board that is used for searching.
    private Searchable<Position> searchable;

    //A* comparator.
    private Comparator<AStarState<Position>> comparator;

    private State<Position> goalState;

    //Algorithm bound.
    private int bound;

    /**
     * Constructor.
     *
     * @param bound algorithm bound
     */
    public AStarSearcher(int bound) {
        this.bound = bound;
        this.comparator = new AStarComparator();
    }

    @Override
    /**
     * Finds a solution using A*.
     */
    public Solution search(Searchable<Position> searchable) {

        this.searchable = searchable;
        this.goalState = this.searchable.getGoalState();

        //Get initial state.
        AStarState<Position> startNode = parseToAStar(searchable.getInitialState());

        //Get goal state.
        AStarState<Position> goalNode = parseToAStar(searchable.getGoalState());

        //Run the A* search.
        AStarState<Position> resultNode = AStar(startNode, goalNode);

        return new Solution(resultNode);
    }

    /**
     * Performs A* search.
     *
     * @param startNode start node
     * @param goalNode  goal node
     * @return goal state containing the path
     */
    private AStarState<Position> AStar(AStarState<Position> startNode, AStarState<Position> goalNode) {

        List<State<Position>>      tempList;
        List<AStarState<Position>> neighbours;
        AStarState<Position>       node;
        int                        boundChecker = 0;

        this.openList = new PriorityQueue<>(comparator);
        this.openList.add(startNode);

        while (!this.openList.isEmpty() && boundChecker < this.bound) {

            //Extract the top node from the open list.
            node = this.openList.remove();

            //Check if found the goal node.
            if (node.equals(goalNode)) {
                return node;
            }

            //Get all the possible states of the current node.
            tempList = this.searchable.getAllPossibleStates(node);

            //Parse the states to A* states.
            neighbours = parseToAStarList(tempList);

            for (AStarState<Position> neighbour : neighbours) {

                if(this.openList.contains(neighbour)){
                    continue;
                }
                //Set new g(n) value.
                neighbour.setG(node.getG() + neighbour.getState().getCost());

                //Set new parent value.
                neighbour.setCameFrom(node);

                //Set new h(n) value.
                neighbour.setH(Heuristics.aStarHeuristicCostEstimate(neighbour.getState(), this.goalState.getState()));

                //Set new f(n) value.
                neighbour.setF(neighbour.getG() + neighbour.getH());

                //Add neighbour to open list.
                this.openList.add(neighbour);
            }

            //TODO should I check the bound here, or inside the for loop right above it?
            boundChecker++;
        }

        return null;
    }

    private AStarState<Position> parseToAStar(State<Position> state) {

        int g = state.getState().getCost();
        int h = Heuristics.aStarHeuristicCostEstimate(state.getState(), this.goalState.getState());

        return new AStarState<>(state.getState(), g, h);
    }

    private List<AStarState<Position>> parseToAStarList(List<State<Position>> tempList) {

        List<AStarState<Position>> aStarStateList = new ArrayList<>();

        //Move states from temp list to the new list.
        for (State<Position> state : tempList) {

            //parse State to AStarState.
            AStarState<Position> aStarState = parseToAStar(state);
            aStarStateList.add(aStarState);
        }

        //Sort the list according to the A* constraints.
        aStarStateList.sort(this.comparator);

        return aStarStateList;
    }

}
