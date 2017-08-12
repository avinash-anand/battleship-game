package battleship.ship;

import battleship.coordinate.Coordinate;
import battleship.direction.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatrolTest {

    private Patrol patrolH;
    private Patrol patrolV;
    @Before
    public void setup() {
        Coordinate startCoordinate = new Coordinate(1,'A');
        patrolH = new Patrol(Direction.HORIZONTAL, startCoordinate);
        patrolV = new Patrol(Direction.VERTICAL, startCoordinate);
    }

    @Test
    public void carrierSizeShouldBe5() {
        assertEquals(2, patrolH.getSize());
        assertEquals(2, patrolV.getSize());
    }

    @Test
    public void horizontalCarriersDirectionIsHorizontal() {
        assertEquals(Direction.HORIZONTAL, patrolH.getDirection());
    }

    @Test
    public void verticalCarriersDirectionIsVertical() {
        assertEquals(Direction.VERTICAL, patrolV.getDirection());
    }

    @Test
    public void endCoordinateShouldBeStartCoordinatePlusSizeInDirection() {
        assertEquals(2, patrolH.getEndCoordinate().getX());
        assertEquals('A', patrolH.getEndCoordinate().getY());
        assertEquals(1, patrolV.getEndCoordinate().getX());
        assertEquals('B', patrolV.getEndCoordinate().getY());
    }

    @Test
    public void testIfHit() {
        assertTrue(patrolH.isHit(new Coordinate(2, 'A')));
        assertTrue(patrolV.isHit(new Coordinate(1, 'B')));
    }

    @Test
    public void testIsSunk() {
        assertTrue(patrolH.isHit(new Coordinate(1, 'A')));
        assertTrue(patrolH.isHit(new Coordinate(2, 'A')));
        assertTrue(patrolH.isSunk());
    }

}
