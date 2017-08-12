package battleship.board;

import battleship.coordinate.Coordinate;
import battleship.direction.Direction;
import battleship.ship.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {


    private int startX = 1;
    private char startY = 'A';
    private int endX;
    private char endY;

    private List<BoardState> boardState;


    public Board(int length, int breadth) {
        assert (length > 0 && length < 26);
        assert (breadth > 0 && breadth < 26);
        endX = startX + length - 1;
        endY = (char) (startY + breadth - 1);
        initializeBoard();
    }

    private Ship firstShip;
    private Ship secondShip;
    private Ship thirdShip;
    private Ship fourthShip;

    public Ship getFirstShip() {
        return firstShip;
    }

    public void setFirstShip(Ship firstShip) {
        this.firstShip = firstShip;
    }

    public Ship getSecondShip() {
        return secondShip;
    }

    public void setSecondShip(Ship secondShip) {
        this.secondShip = secondShip;
    }

    public Ship getThirdShip() {
        return thirdShip;
    }

    public void setThirdShip(Ship thirdShip) {
        this.thirdShip = thirdShip;
    }

    public Ship getFourthShip() {
        return fourthShip;
    }

    public void setFourthShip(Ship fourthShip) {
        this.fourthShip = fourthShip;
    }

    private Direction randomDirection() {
        if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
            return Direction.HORIZONTAL;
        } else {
            return Direction.VERTICAL;
        }
    }

    private Coordinate randomStartCoordinate() {
        int x = ThreadLocalRandom.current().nextInt(1, endX);
        char y = (char) ThreadLocalRandom.current().nextInt(65, endY);
        return new Coordinate(x, y);
    }

    private Ship randomShip() {
        int x = ThreadLocalRandom.current().nextInt(2, 6);
        Direction shipDirection = randomDirection();
        Coordinate start = randomStartCoordinate();
        if (x == 2) {
            return new Patrol(shipDirection, start);
        } else if (x == 3) {
            return new Submarine(shipDirection, start);
        } else if (x == 4) {
            return new Battleship(shipDirection, start);
        } else {
            return new Carrier(shipDirection, start);
        }
    }

    private boolean isShipOnBoard(Ship ship) {
        if (ship.getEndCoordinate().getX() < endX && ship.getEndCoordinate().getY() < endY) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isShipOverLapping(Ship existing, Ship newShip) {
        if (newShip.getStartCoordinate().getX() >= existing.getStartCoordinate().getX() &&
                newShip.getStartCoordinate().getX() <= existing.getEndCoordinate().getX()) {
            return true;
        } else if (newShip.getStartCoordinate().getY() >= existing.getStartCoordinate().getY() &&
                newShip.getStartCoordinate().getY() <= existing.getEndCoordinate().getY()) {
            return true;
        } else {
            return false;
        }
    }

    private void initializeBoard() {
        int counter = 1;
//        for(int i =1;i<=4;) {
//            Ship ship = randomShip();
//            Coordinate coordinate = randomStartCoordinate();
//            if(canShipBePlacedAtThisCoordinate(ship,coordinate)) {
//                placeShip(ship, coordinate);
//                i++;
//            }
//        }
        while (counter <= 4) {
            Ship ship = randomShip();
            switch (counter) {
                case 1: {
                    if (isShipOnBoard(ship)) {
                        this.setFirstShip(ship);
                        counter++;
                    }
                    break;
                }
                case 2: {
                    if (isShipOnBoard(ship)) {
                        if (!isShipOverLapping(this.firstShip, ship)) {
                            this.setSecondShip(ship);
                            counter++;
                        }
                    }
                    break;
                }
                case 3: {
                    if (isShipOnBoard(ship)) {
                        if (!isShipOverLapping(this.firstShip, ship) &&
                                !isShipOverLapping(this.secondShip, ship)) {
                            this.setThirdShip(ship);
                            counter++;
                        }
                    }
                    break;
                }
                case 4: {
                    if (isShipOnBoard(ship)) {
                        if (!isShipOverLapping(this.firstShip, ship) &&
                                !isShipOverLapping(this.secondShip, ship) &&
                                !isShipOverLapping(this.thirdShip, ship)) {
                            this.setFourthShip(ship);
                            counter++;
                        }
                    }
                    break;
                }
            }
        }
        initializeBoardState();
    }

    private void initializeBoardState() {
        this.boardState = new ArrayList<>();
        for (char j = 'A'; j <= endY; j++) {
            for (char i = 1; i <= endX; i++) {
                BoardState state = new BoardState();
                state.setCoordinate(new Coordinate(i, j));
                boolean isOnShip = false;
                for (int k = 1; k <= 4; k++) {
                    switch (k) {
                        case 1: {
                            isOnShip = isCoordinateOnShip(new Coordinate(i, j), firstShip);
                            break;
                        }
                        case 2: {
                            isOnShip = isCoordinateOnShip(new Coordinate(i, j), secondShip);
                            break;
                        }
                        case 3: {
                            isOnShip = isCoordinateOnShip(new Coordinate(i, j), thirdShip);
                            break;
                        }
                        case 4: {
                            isOnShip = isCoordinateOnShip(new Coordinate(i, j), fourthShip);
                            break;
                        }
                    }
                    if (isOnShip) {
                        break;
                    }
                }
                state.setShipAtThisCoordinate(isOnShip);
                state.setThisCoordinateHit(false);
                boardState.add(state);
            }
        }
    }

    private boolean isCoordinateOnShip(Coordinate coordinate, Ship ship) {
        return (coordinate.getX() >= ship.getStartCoordinate().getX() &&
                coordinate.getX() <= ship.getEndCoordinate().getX() &&
                coordinate.getY() >= ship.getStartCoordinate().getY() &&
                coordinate.getY() <= ship.getEndCoordinate().getY());
    }

    private void updateBoardCoordinateHitState(Coordinate coordinate) {
        Iterator<BoardState> boardStateIterator = boardState.iterator();
        while (boardStateIterator.hasNext()) {
            BoardState state = boardStateIterator.next();
            if (state.getCoordinate().equals(coordinate)) {
                state.setThisCoordinateHit(true);
            }
        }
    }

    public void coordinateHit(Coordinate coordinate) {
        updateBoardCoordinateHitState(coordinate);

//        for (Ship s: shipCollection) {
//            if(s.isHit(coordinate)) {
//                if(s.isSunk()){
//                    return "SINK";
//                }
//                return "HIT";
//            }
//        }
//        return "MISS";


        if (firstShip.isHit(coordinate) ||
                secondShip.isHit(coordinate) ||
                thirdShip.isHit(coordinate) ||
                fourthShip.isHit(coordinate)) {
            if (firstShip.isSunk()) {
                System.out.println("SINK");
            } else if (secondShip.isSunk()) {
                System.out.println("SINK");
            } else if (thirdShip.isSunk()) {
                System.out.println("SINK");
            } else if (fourthShip.isSunk()) {
                System.out.println("SINK");
            } else {
                System.out.println("HIT");
            }
        } else {
            System.out.println("MISS");
        }
//        System.out.println(boardState);
    }

//    private void printSinkMessage

    public void printBoard() {
        int counter = startX;
        for(BoardState state: boardState) {
            if(counter > endX) {
                counter = startX;
                System.out.println();
            }
            if(state.isShipAtThisCoordinate()) {
                if(state.isThisCoordinateHit()) {
                    System.out.print('H');
                } else {
                    System.out.print('S');
                }
            } else if(state.isThisCoordinateHit()) {
                System.out.print('H');
            } else {
                System.out.print('E');
            }
            counter ++;
        }
    }

    protected List<BoardState> getBoardState() {
        return this.boardState;
    }

//    private boolean canShipBePlacedAtThisCoordinate(Ship ship, Coordinate coordinate) {
//        Iterator<BoardState> boardStateIterator = boardState.iterator();
//        int shipSize = ship.getSize();
//        Direction shipDirection = ship.getDirection();
//        boolean canBePlaced = false;
//        int x;
//        if(shipDirection == Direction.HORIZONTAL) {
//            while (boardStateIterator.hasNext()) {
//                Coordinate coordinate1 = new Coordinate();
//                BoardState state = boardStateIterator.next();
////                state.isShipAtThisCoordinate(coordinate);
//            }
//        } else {
//
//        }
////        while (boardStateIterator.hasNext()) {
////            BoardState state = boardStateIterator.next();
////            state.isShipAtThisCoordinate()
////        }
//        return false;
//    }

}
