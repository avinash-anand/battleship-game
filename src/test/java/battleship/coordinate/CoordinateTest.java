package battleship.coordinate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {



    @Before
    public void setup() {

    }

    @Test
    public void testCoordinateEquality() {
        assertEquals(new Coordinate(1,'A'), new Coordinate(1, 'A'));
    }

}
