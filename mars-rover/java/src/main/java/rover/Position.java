package rover;

/**
 * Created by olivier on 18/02/16.
 */
public class Position {
    private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {

        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Position))
            return false;

        Position position = (Position) o;

        if (x != position.x)
            return false;
        if (y != position.y)
            return false;
        return orientation == position.orientation;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        return result;
    }
}
