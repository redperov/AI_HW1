/**
 * The class represents a position in a board.
 * It consists of (x,y) coordinates, and a char value.
 */
public class Position {

    private int     x;
    private int     y;
    private char    value;
    private int     cost;
    private boolean isLegal;

    /**
     * Constructor.
     *
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param value cell value
     */
    public Position(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;

        this.isLegal = true;

        //Set the position cost.
        switch (this.getValue()) {

            case 'S':
                this.cost = 0;
                break;
            case 'G':
                this.cost = 0;
                break;
            case 'R':
                this.cost = 1;
                break;
            case 'D':
                this.cost = 3;
                break;
            case 'H':
                this.cost = 10;
                break;
            case 'W':
                this.isLegal = false;
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
     * Is a legal road getter.
     *
     * @return boolean
     */
    public boolean isLegal() {
        return isLegal;
    }

    @Override
    /**
     * Checks if the (x,y) coordinates and the value are equal to the ones of the given position.
     */
    public boolean equals(Object obj) {

        Position otherPosition = (Position) obj;

        return (this.getX() == otherPosition.getX()) && (this.getY() == otherPosition.getY()
                && (this.getValue() == otherPosition.getValue()));
    }
}
