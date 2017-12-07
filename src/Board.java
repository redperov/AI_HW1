import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a map with square board.
 * The board consists of (x,y) coordinates, and a char value which represents a certain road.
 */
public class Board implements Searchable<Position> {

    private char[][]        board;
    private int             boardSize;
    private State<Position> initialState;
    private State<Position> goalState;

    /**
     * Constructor.
     * @param boardSize board size
     */
    public Board(int boardSize) {

        //Initializes the board.
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];

        //Sets the initial and goal positions.
        Position initialPosition = new Position(0, 0, this.board[0][0]);
        Position goalPosition = new Position(this.boardSize - 1, this.boardSize - 1,
                this.board[this.boardSize - 1][this.boardSize - 1]);

        //Sets the initial and goal states.
        this.initialState = new State<>(initialPosition);
        this.goalState = new State<>(goalPosition);
    }

    @Override
    /**
     * Returns the top-left cell.
     */
    public State<Position> getInitialState() {

        return this.initialState;
    }

    @Override
    /**
     * Returns the bottom-right cell.
     */
    public State<Position> getGoalState() {

        return this.goalState;
    }

    @Override
    /**
     * Sets a value to a board cell.
     */
    public void addState(State<Position> state) {

        int  x     = state.getState().getX();
        int  y     = state.getState().getY();
        char value = state.getState().getValue();

        this.board[x][y] = value;
    }

    @Override
    /**
     * Returns a list of all the cells around a given cell.
     */
    public List<State<Position>> getAllPossibleStates(State<Position> state) {

        //A list of all the cells neighbours.
        List<State<Position>> neighbours = new ArrayList<>();
        Position              neighbourPosition;
        State<Position>       neighbourState;
        int                   row          = state.getState().getX();
        int                   column          = state.getState().getY();


        //Check if cell has a bottom-right neighbour.
        if (row < this.boardSize - 1 && column < this.boardSize - 1) {

            char neighbourValue = this.board[row + 1][column + 1];
            neighbourPosition = new Position(row + 1, column + 1, neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a bottom neighbour.
        if(row < this.boardSize - 1){

            char neighbourValue = this.board[row + 1][column];
            neighbourPosition = new Position(row + 1, column, neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a bottom-left neighbour.
        if(row < this.boardSize - 1 && column > 0){

            char neighbourValue = this.board[row + 1][column - 1];
            neighbourPosition = new Position(row + 1, column - 1, neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a left neighbour.
        if(column > 0){

            char neighbourValue = this.board[row][column - 1];
            neighbourPosition = new Position(row, column - 1, neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a top-left neighbour.
        if(row > 0 && column > 0){

            char neighbourValue = this.board[row - 1][column - 1];
            neighbourPosition = new Position(row - 1, column - 1, neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a top neighbour.
        if(row > 0){

            char neighbourValue = this.board[row - 1][column];
            neighbourPosition = new Position(row - 1, column , neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a top-right neighbour.
        if(row > 0 && column < this.boardSize - 1){

            char neighbourValue = this.board[row - 1][column + 1];
            neighbourPosition = new Position(row - 1, column + 1 , neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        //Check if cell has a right neighbour.
        if(column < this.boardSize - 1){

            char neighbourValue = this.board[row][column + 1];
            neighbourPosition = new Position(row, column + 1 , neighbourValue);
            neighbourState = new State<>(neighbourPosition);
            neighbours.add(neighbourState);
        }

        return neighbours;
    }
}
