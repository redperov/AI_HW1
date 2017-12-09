/**
 * The class represents a position in a 2D board.
 * It consists of (x,y) coordinates, and a char value.
 */
public class Position {

    //X coordinate.
    private int x;

    //Y coordinate.
    private int y;

    //Board value at position (x,y)
    private char value;

    //Cost to reach the position.
    private int cost;

    //The direction from which the position came from.
    private int directionCameFrom;

    //Is the position a legal road.
    private boolean isLegal;

    /**
     * Constructor.
     *
     * @param x                 X coordinate
     * @param y                 Y coordinate
     * @param value             cell value
     * @param directionCameFrom direction from which the position came from.
     */
    public Position(int x, int y, char value, int directionCameFrom) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.directionCameFrom = directionCameFrom;

        this.isLegal = true;

        //Set the position cost.
        switch (this.getValue()) {

            //Start position.
            case 'S':
                this.cost = 0;
                break;

            //Goal position.
            case 'G':
                this.cost = 0;
                break;

            //Paved road.
            case 'R':
                this.cost = 1;
                break;

            //Unpaved road.
            case 'D':
                this.cost = 3;
                break;

            //Hill.
            case 'H':
                this.cost = 10;
                break;

            //Water.
            case 'W':
                this.isLegal = false;
                break;

            default:
                break;
        }
    }

    /**
     * X getter.
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Y getter.
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Value getter.
     *
     * @return char
     */
    public char getValue() {
        return value;
    }

    /**
     * Cost getter.
     *
     * @return int
     */
    public int getCost() {
        return cost;
    }

    /**
     * Direction came from getter.
     *
     * @return direction: 0 - right, 1 - bottom-right, 2 - bottom, ..., 7 - top-right
     */
    public int getDirectionCameFrom() {
        return directionCameFrom;
    }

    /**
     * Is a legal road getter.
     *
     * @return boolean
     */
    public boolean isLegal() {
        return isLegal;
    }

    @Override
    /**
     * Checks if the (x,y) coordinates are equal to the ones of the given position.
     */
    public boolean equals(Object obj) {

        Position otherPosition = (Position) obj;

        return (this.getX() == otherPosition.getX()) && (this.getY() == otherPosition.getY());
    }

    @Override
    /**
     * Returns the string representation of the position's direction.
     */
    public String toString() {

        //If the position is the initial state.
        if (this.value == 'S') {
            return "S";
        }

        //For each direction number return the string representation of it.
        switch (this.directionCameFrom) {

            //Right.
            case 0:
                return "R";

            //Right-down.
            case 1:
                return "RD";

            //Down.
            case 2:
                return "D";

            //Left-down.
            case 3:
                return "LD";

            //Left.
            case 4:
                return "L";

            //Left-up.
            case 5:
                return "LU";

            //Up.
            case 6:
                return "U";

            //Right-up.
            case 7:
                return "RU";
        }

        return "";
    }
}
