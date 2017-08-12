package battleship.board;


import battleship.coordinate.Coordinate;

public class BoardState {

    private Coordinate coordinate;
    private boolean shipAtThisCoordinate;
    private boolean thisCoordinateHit;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isShipAtThisCoordinate() {
        return shipAtThisCoordinate;
    }

    public void setShipAtThisCoordinate(boolean shipAtThisCoordinate) {
        this.shipAtThisCoordinate = shipAtThisCoordinate;
    }

    public boolean isThisCoordinateHit() {
        return thisCoordinateHit;
    }

    public void setThisCoordinateHit(boolean thisCoordinateHit) {
        this.thisCoordinateHit = thisCoordinateHit;
    }

    @Override
    public String toString() {
        return "BoardState{" +
                "coordinate=" + coordinate +
                ", shipAtThisCoordinate=" + shipAtThisCoordinate +
                ", thisCoordinateHit=" + thisCoordinateHit +
                '}';
    }
}
