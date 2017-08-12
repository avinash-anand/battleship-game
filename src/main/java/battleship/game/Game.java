package battleship.game;

import battleship.board.Board;
import battleship.coordinate.Coordinate;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board(10, 10);
    }

    public void shoot(Coordinate coordinate) {
        this.board.coordinateHit(coordinate);
    }

    public Board getBoard() {
        return this.board;
    }

}
