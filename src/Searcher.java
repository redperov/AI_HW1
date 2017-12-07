/**
 * The interface represents searcher object.
 * It has the functionality to search a Searchable object.
 */
public interface Searcher<T> {

    /**
     * Finds a solution to a given Searchable object.
     * @param searchable searchable
     * @return solution
     */
    Solution search(Searchable<T> searchable);

    //TODO implement if needed, then create an abstract class Searcher and add it as a method to it
    //int getNumberOfNodesEvaluated();
}
