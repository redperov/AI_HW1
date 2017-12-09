/**
 * This is a static class which contains methods to calculate heuristics.
 * Currently contains the heuristic needed for the A* search.
 */
public class Heuristics {

    /**
     * Private constructor.
     */
    private Heuristics() {
    }

    /**
     * Calculates aerial distance between to positions.
     *
     * @param current current position
     * @param goal    goal position
     * @return aerial distance
     */
    public static int aStarHeuristicCostEstimate(Position current, Position goal) {

        return Math.max(Math.abs(goal.getX() - current.getX()), Math.abs(goal.getY() - current.getY()));
    }
}
