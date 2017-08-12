package battleship.ship;

import battleship.coordinate.Coordinate;
import battleship.direction.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Battleship extends Ship {

    private int size;
    private Direction direction;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private Map<Coordinate, Boolean> shipState = new HashMap<>();

    public Battleship(Direction direction, Coordinate startCoordinate) {
        this.size = 4;
        this.direction = direction;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = calculateEndCoordinate(startCoordinate, direction);
        initializeShipState();
    }

    public int getSize() {
        return this.size;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Coordinate getStartCoordinate() {
        return this.startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return this.endCoordinate;
    }

    public boolean isHit(Coordinate coordinate) {
        boolean isHit = false;
        switch (direction) {
            case HORIZONTAL: {
                if (coordinate.getY() == startCoordinate.getY() &&
                        coordinate.getX() <= endCoordinate.getX() &&
                        coordinate.getX() >= startCoordinate.getX()) {
                    isHit = true;
                    updateShipState(coordinate);
                }
                break;
            }
            case VERTICAL: {
                if (coordinate.getX() == startCoordinate.getX() &&
                        coordinate.getY() >= startCoordinate.getY() &&
                        coordinate.getY() <= endCoordinate.getY()) {
                    isHit = true;
                    updateShipState(coordinate);
                }
                break;
            }
        }
        return isHit;
    }

    public boolean isSunk() {
        boolean isSunk = true;
        for(Map.Entry<Coordinate, Boolean> e : shipState.entrySet()) {
            if(!e.getValue()) {
                isSunk = false;
            }
        }
        return isSunk;
    }

    private Coordinate calculateEndCoordinate(Coordinate startCoordinate, Direction direction) {
        Coordinate endCoordinate = new Coordinate();
        switch (direction) {
            case HORIZONTAL: {
                // including start coordinate, so minus 1
                endCoordinate.setX(startCoordinate.getX() + this.size - 1);
                endCoordinate.setY(startCoordinate.getY());
                break;
            }
            case VERTICAL: {
                endCoordinate.setX(startCoordinate.getX());
                // including start coordinate, so minus 1
                endCoordinate.setY((char)(startCoordinate.getY()+ this.size - 1));
                break;
            }
        }
        return endCoordinate;
    }

    private void initializeShipState() {
        switch (direction) {
            case HORIZONTAL: {
                for (int i = startCoordinate.getX(); i <= endCoordinate.getX(); i++) {
                    Coordinate coordinate = new Coordinate(i, startCoordinate.getY());
                    shipState.put(coordinate, false);
                }
                break;
            }
            case VERTICAL: {
                for (int i = startCoordinate.getY(); i <= endCoordinate.getY(); i++) {
                    Coordinate coordinate = new Coordinate(startCoordinate.getX(), (char) i);
                    shipState.put(coordinate, false);
                }
                break;
            }
        }
    }

    private void updateShipState(Coordinate coordinate) {
        shipState.replace(coordinate, false, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battleship that = (Battleship) o;
        return size == that.size &&
                direction == that.direction &&
                Objects.equals(startCoordinate, that.startCoordinate) &&
                Objects.equals(endCoordinate, that.endCoordinate) &&
                Objects.equals(shipState, that.shipState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, direction, startCoordinate, endCoordinate, shipState);
    }
}
