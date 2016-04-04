package rover;

import static rover.Orientation.*;

public class MarsRover {

    private Position position;
    private Planet planet;

    public MarsRover() {
        this(0, 0, NORTH);
    }

    public MarsRover(int x, int y, Orientation orientation) throws IllegalStateException {
        this(x, y, orientation, new Planet());
    }

    public MarsRover(int x, int y, Orientation orientation, Planet p) throws IllegalStateException {
        if (p.exists(x, y)) {
            throw new IllegalStateException("Out of the planet");
        }
        if (p.hasObstacle(x, y)) {
            throw new IllegalStateException("On a obstacle");
        }
        this.position = new Position(x,y,orientation);
        this.planet = p;
    }

    public Position getPosition() {
        return position;
    }

    public void move(char... directions) throws ObstacleException {

        for (char direction : directions) {
            Position previousPosition = this.position;
            switch (direction) {
            case ('f'):
                step(1);
                break;
            case ('b'):
                step(-1);
                break;
            case ('r'):
                this.position = new Position(this.position.getX(), this.position.getY(), nextOrientation(1));
                break;
            case ('l'):
                this.position = new Position(this.position.getX(), this.position.getY(), nextOrientation(-1));
                break;
            }
            if (planet.hasObstacle(this.position.getX(), this.position.getY())) {
                this.position= previousPosition;
                throw new ObstacleException();
            }
        }

    }

    private void step(int step) {
        switch (this.getPosition().getOrientation()) {
        case NORTH:
            this.position = new Position(this.position.getX(), (this.position.getY() + step) % planet.size, this.getPosition().getOrientation());
            break;
        case SOUTH:
            this.position = new Position(this.position.getX(), (this.position.getY() + planet.size - step) % planet.size , this.getPosition().getOrientation());
            break;
       case WEST:
           this.position = new Position((this.position.getX() + planet.size - step) %  planet.size , this.position.getY(), this.getPosition().getOrientation());
            break;
        case EAST:
            this.position = new Position((this.position.getX() + step) % planet.size, this.position.getY(), this.getPosition().getOrientation());
            break;
        }
    }

    private Orientation nextOrientation(int move) {
        int orientationIndex = Orientation.values().length + this.position.getOrientation().ordinal();
        return Orientation.values()[(orientationIndex + move) % Orientation.values().length];
    }

}
