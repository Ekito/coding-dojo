package rover;

/**
 * Created by olivier on 10/03/16.
 */
public class Obstacle {
    public final int x;
    public final int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Obstacle))
            return false;

        Obstacle obstacle = (Obstacle) o;

        if (x != obstacle.x)
            return false;
        return y == obstacle.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
