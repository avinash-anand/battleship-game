package battleship.ship;

import static org.junit.Assert.*;

import battleship.coordinate.Coordinate;
import battleship.direction.Direction;
import org.junit.Before;
import org.junit.Test;


public class CarrierTest {

    private Carrier carrierH;
    private Carrier carrierV;
    @Before
    public void setup() {
        Coordinate startCoordinate = new Coordinate(1,'A');
        carrierH = new Carrier(Direction.HORIZONTAL, startCoordinate);
        carrierV = new Carrier(Direction.VERTICAL, startCoordinate);
    }

    @Test
    public void carrierSizeShouldBe5() {
        assertEquals(5, carrierH.getSize());
        assertEquals(5, carrierV.getSize());
    }

    @Test
    public void horizontalCarriersDirectionIsHorizontal() {
        assertEquals(Direction.HORIZONTAL, carrierH.getDirection());
    }

    @Test
    public void verticalCarriersDirectionIsVertical() {
        assertEquals(Direction.VERTICAL, carrierV.getDirection());
    }

    @Test
    public void endCoordinateShouldBeStartCoordinatePlusSizeInDirection() {
        assertEquals(5, carrierH.getEndCoordinate().getX());
        assertEquals('A', carrierH.getEndCoordinate().getY());
        assertEquals(1, carrierV.getEndCoordinate().getX());
        assertEquals('E', carrierV.getEndCoordinate().getY());
    }

    @Test
    public void testIfHit() {
        assertTrue(carrierH.isHit(new Coordinate(2, 'A')));
        assertTrue(carrierV.isHit(new Coordinate(1, 'B')));
    }

    @Test
    public void testIsSunk() {
        assertTrue(carrierH.isHit(new Coordinate(1, 'A')));
        assertTrue(carrierH.isHit(new Coordinate(2, 'A')));
        assertTrue(carrierH.isHit(new Coordinate(3, 'A')));
        assertTrue(carrierH.isHit(new Coordinate(4, 'A')));
        assertTrue(carrierH.isHit(new Coordinate(5, 'A')));
        assertTrue(carrierH.isSunk());
    }

}
