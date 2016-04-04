import org.junit.Test;
import rover.MarsRover;
import rover.Obstacle;
import rover.ObstacleException;
import rover.Planet;
import rover.Position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static rover.Orientation.EAST;
import static rover.Orientation.NORTH;
import static rover.Orientation.SOUTH;
import static rover.Orientation.WEST;

public class TestMarsOver {

    @Test
    public void testInitialPosition() {
        MarsRover rover = new MarsRover();

        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(NORTH);
    }

   @Test
    public void testInitialPositionSouth() {
        MarsRover rover = new MarsRover(0,0,SOUTH);

        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(SOUTH);
    }

    @Test
    public void testForwardOneStep() throws ObstacleException {
        MarsRover rover = new MarsRover();

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(1);
        assertThat(position.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void testForwardOneStepSouth() throws ObstacleException {
        MarsRover rover = new MarsRover(0,1,SOUTH);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(SOUTH);
    }


    @Test
    public void testForwardOneStepWest() throws ObstacleException {
        MarsRover rover = new MarsRover(22,22,WEST);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(21);
        assertThat(position.getY()).isEqualTo(22);
        assertThat(position.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testTurnRight() throws ObstacleException {
        MarsRover rover = new MarsRover();

        rover.move('r');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(EAST);
    }

    @Test
    public void testTurnRightSouth() throws ObstacleException {
        MarsRover rover = new MarsRover(0,0,SOUTH);

        rover.move('r');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testTurnRightPosition5() throws ObstacleException {
        MarsRover rover = new MarsRover(5,5,SOUTH);

        rover.move('r');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(5);
        assertThat(position.getY()).isEqualTo(5);
        assertThat(position.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testMoveRightPositionWEST() throws ObstacleException {
        MarsRover rover = new MarsRover(5,5, WEST);

        rover.move('r');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(5);
        assertThat(position.getY()).isEqualTo(5);
        assertThat(position.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void testMoveLeftPositionWEST() throws ObstacleException {
        MarsRover rover = new MarsRover(5,5, NORTH);

        rover.move('l');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(5);
        assertThat(position.getY()).isEqualTo(5);
        assertThat(position.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testBackwardOneStep() throws ObstacleException {
        MarsRover rover = new MarsRover();

        rover.move('b');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(-1);
        assertThat(position.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void testBackwardOneStepWest() throws ObstacleException {
        MarsRover rover = new MarsRover(23,23,WEST);

        rover.move('b');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(24);
        assertThat(position.getY()).isEqualTo(23);
        assertThat(position.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testBackwardTwoStep() throws ObstacleException {
        MarsRover rover = new MarsRover(23,23,WEST);

        rover.move('b', 'b');
        Position position = rover.getPosition();

        assertThat(position).isEqualToComparingFieldByField(new Position(25,23, WEST));
    }

    @Test(expected = IllegalStateException.class)
    public void testOutOfTheGrid() {
        new MarsRover(26,26,EAST);
    }

    @Test(expected = IllegalStateException.class)
    public void testPlanetShouldValidateCoordinales() {
        Planet planet = new Planet(5);
        new MarsRover(6,0,EAST, planet);
    }

    @Test(expected = IllegalStateException.class)
    public void testLandOnObstacle() {
        Planet mars = new Planet();
        mars.add(new Obstacle(0,0));
        new MarsRover(0,0,EAST, mars);
    }

    @Test
    public void testGoToObstacle() {
        Planet mars = new Planet();
        mars.add(new Obstacle(0,1));
        MarsRover rover = new MarsRover(0,0,NORTH, mars);
        try {
            rover.move('f');
            fail("Should have reported obstacle");
        } catch (ObstacleException obstacleException) {
            assertThat(rover.getPosition()).isEqualTo(new Position(0,0, NORTH));
        }

    }

    @Test
    public void testWrapGridEast() throws ObstacleException {
        MarsRover rover = new MarsRover(25,25,EAST);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(25);
        assertThat(position.getOrientation()).isEqualTo(EAST);
    }

    @Test
    public void testWrapGridWest() throws ObstacleException {
        MarsRover rover = new MarsRover(0,0,WEST);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(25);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testWrapGridNorth() throws ObstacleException {
        MarsRover rover = new MarsRover(25,25, NORTH);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(25);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void testWrapGridSouth() throws ObstacleException {
        MarsRover rover = new MarsRover(0, 0, SOUTH);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(25);
        assertThat(position.getOrientation()).isEqualTo(SOUTH);
    }

    @Test
    public void testGoOutBoundaryEastPlanetSize() throws ObstacleException {
        Planet mars = new Planet(28);
        MarsRover rover = new MarsRover(25,25,EAST, mars);

        rover.move('f');
        Position position = rover.getPosition();

        assertThat(position.getX()).isEqualTo(26);
        assertThat(position.getY()).isEqualTo(25);
        assertThat(position.getOrientation()).isEqualTo(EAST);
    }
}