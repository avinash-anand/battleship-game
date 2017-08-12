package battleship.ship;

import battleship.coordinate.Coordinate;
import battleship.direction.Direction;

public abstract class Ship {

//    public Ship(Direction direction, Coordinate startCoordinate) {
//
//    }

    public abstract int getSize();

    public abstract Direction getDirection();

    public abstract Coordinate getStartCoordinate();

    public abstract Coordinate getEndCoordinate();

    public abstract boolean isHit(Coordinate coordinate);

    public abstract boolean isSunk();

}
