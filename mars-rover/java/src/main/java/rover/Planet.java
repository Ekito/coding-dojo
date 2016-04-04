package rover;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by olivier on 10/03/16.
 */
public class Planet {

    public final int size;
    private List<Obstacle> obstacles = new LinkedList<>();

    public Planet(int size) {

        this.size = size;
    }

    public Planet() {
        this(26);
    }

    public void add(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    public boolean hasObstacle(int x, int y) {
        return this.obstacles.contains(new Obstacle(x, y));
    }

    boolean exists(int x, int y) {
        return x/ size != 0 || y/ size != 0;
    }
}
