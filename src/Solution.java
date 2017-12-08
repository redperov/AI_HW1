import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class represents a solution to a searching problem.
 * It contains the path of the solution.
 */
public class Solution {

    //List of actions.
    private List<State<Position>> actions;

    /**
     * Constructor.
     *
     * @param goalState goal state
     */
    public Solution(State<Position> goalState) {

        //TODO maybe change to stack to avoid reverse
        this.actions = new ArrayList<>();
        State<Position> temp = goalState;

        //Backtrack to the initial state.
        while (temp != null) {

            //Add each state to the actions list.
            actions.add(temp);
            temp = temp.getCameFrom();
        }
    }

    @Override
    /**
     * Returns a string representation of the search solution.
     */
    public String toString() {

        //Check if a solution was found.
        //TODO check if in the case of no solution the list is actually empty and not just with one or two nodes
        if (actions.isEmpty()) {
            return "no path";
        }

        //Reverse the list.
        Collections.reverse(this.actions);

        //Remove the initial state from the list.
        actions.remove(0);

        StringBuilder stringBuilder = new StringBuilder();
        int           sumOfCost     = 0;

        //Turn all the actions in a string.
        for (State<Position> action : actions) {

            //Concatenate directions.
            stringBuilder.append(action.getState().toString());
            stringBuilder.append("-");

            //Sum the directions cost.
            sumOfCost += action.getState().getCost();
        }

         stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        //Concatenate the cost to the answer.
        stringBuilder.append(" ").append(Integer.toString(sumOfCost));

        return stringBuilder.toString();

    }
}
