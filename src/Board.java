import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a map with square board.
 * The board consists of (x,y) coordinates, and a char value which represents a certain road.
 */
public class Board implements Searchable<Position> {

    //Char matrix which represents the board.
    private char[][] board;

    //Board size.
    private int boardSize;

    //Initial state.
    private State<Position> initialState;

    //Goal state.
    private State<Position> goalState;

    //Neighbours list.
    private List<State<Position>> neighbours;

    /**
     * Constructor.
     *
     * @param boardSize board size
     */
    public Board(int boardSize) {

        //Initializes the board.
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];

        //Sets the initial and goal positions.
        Position initialPosition = new Position(0, 0, this.board[0][0], -1);
        Position goalPosition = new Position(this.boardSize - 1, this.boardSize - 1,
                this.board[this.boardSize - 1][this.boardSize - 1], -1);

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
    public void addValue(String value) {

        //Split the string to x y coordinates, and road type
        String[] parts    = value.split(" ");
        int      x        = Integer.parseInt(parts[0]);
        int      y        = Integer.parseInt(parts[1]);
        char     roadType = parts[2].charAt(0);

        //Set value in the board.
        this.board[x][y] = roadType;
    }

    @Override
    /**
     * Returns a list of all the cells around a given cell.
     */
    public List<State<Position>> getAllPossibleStates(State<Position> state) {

        //A list of all the cells neighbours.
        this.neighbours = new ArrayList<>();

        //The given state's coordinates.
        int row    = state.getState().getX();
        int column = state.getState().getY();

        int neighbourRow;
        int neighbourColumn;
        int neighbourDirection;

        //Increments creation time by 1.
        State.increaseTimer();

        //Check if cell has a right neighbour.
        if (column < this.boardSize - 1) {

            neighbourRow = row;
            neighbourColumn = column + 1;
            neighbourDirection = 0;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a bottom-right neighbour.
        if (row < this.boardSize - 1 && column < this.boardSize - 1) {

            neighbourRow = row + 1;
            neighbourColumn = column + 1;
            neighbourDirection = 1;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a bottom neighbour.
        if (row < this.boardSize - 1) {

            neighbourRow = row + 1;
            neighbourColumn = column;
            neighbourDirection = 2;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a bottom-left neighbour.
        if (row < this.boardSize - 1 && column > 0) {

            neighbourRow = row + 1;
            neighbourColumn = column - 1;
            neighbourDirection = 3;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a left neighbour.
        if (column > 0) {

            neighbourRow = row;
            neighbourColumn = column - 1;
            neighbourDirection = 4;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a top-left neighbour.
        if (row > 0 && column > 0) {

            neighbourRow = row - 1;
            neighbourColumn = column - 1;
            neighbourDirection = 5;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a top neighbour.
        if (row > 0) {

            neighbourRow = row - 1;
            neighbourColumn = column;
            neighbourDirection = 6;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        //Check if cell has a top-right neighbour.
        if (row > 0 && column < this.boardSize - 1) {

            neighbourRow = row - 1;
            neighbourColumn = column + 1;
            neighbourDirection = 7;

            //Add neighbour to the list if it's legal.
            addLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection);
        }

        return neighbours;
    }

    /**
     * Checks if the neighbour is legal.
     *
     * @param row               row
     * @param column            column
     * @param diagonalDirection if a diagonal neighbour: 1,3,5,7 else 0
     * @return is legal
     */
    private boolean isLegalNeighbour(int row, int column, int diagonalDirection) {

        //Check if the neighbour is a water road.
        if (this.board[row][column] == 'W') {
            return false;

            //Check if the neighbour is a diagonal.
        } else if (diagonalDirection != 0) {

            //Check if one of the diagonal's neighbours is a water road.
            switch (diagonalDirection) {

                //If bottom-right.
                case 1:
                    if (this.board[row - 1][column] == 'W' || this.board[row][column - 1] == 'W') {
                        return false;
                    }
                    break;

                //If bottom-left.
                case 3:
                    if (this.board[row - 1][column] == 'W' || this.board[row][column + 1] == 'W') {
                        return false;
                    }
                    break;

                //If top-left.
                case 5:
                    if (this.board[row + 1][column] == 'W' || this.board[row][column + 1] == 'W') {
                        return false;
                    }
                    break;

                //If top-right.
                case 7:
                    if (this.board[row + 1][column] == 'W' || this.board[row][column - 1] == 'W') {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }

    /**
     * Adds a neighbour to the neighbours list if it's legal.
     *
     * @param neighbourRow       neighbour row
     * @param neighbourColumn    neighbour column
     * @param neighbourDirection neighbour direction
     */
    private void addLegalNeighbour(int neighbourRow, int neighbourColumn, int neighbourDirection) {

        Position        neighbourPosition;
        State<Position> neighbourState;
        char            neighbourValue;

        //Check if the neighbour is legal.
        if (isLegalNeighbour(neighbourRow, neighbourColumn, neighbourDirection)) {

            //Get board value.
            neighbourValue = this.board[neighbourRow][neighbourColumn];
            neighbourPosition = new Position(neighbourRow, neighbourColumn, neighbourValue, neighbourDirection);
            neighbourState = new State<>(neighbourPosition);

            //Add neighbour to the neighbours list.
            this.neighbours.add(neighbourState);
        }
    }
}
