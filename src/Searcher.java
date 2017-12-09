/**
 * The interface represents a searcher object.
 * It has the functionality to search a Searchable object.
 */
public interface Searcher<T> {

    /**
     * Finds a solution to a given Searchable object.
     *
     * @param searchable searchable
     * @return solution
     */
    Solution search(Searchable<T> searchable);
}
