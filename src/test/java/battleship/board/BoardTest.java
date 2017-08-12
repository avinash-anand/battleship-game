package battleship.board;

import battleship.coordinate.Coordinate;
import battleship.game.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private static Board board;

    @Before
    public void setup() {
        board = new Board(10, 10);
    }

    private static Coordinate getShipPosition(Board board) {
        Coordinate c = new Coordinate();
        for(BoardState state : board.getBoardState()) {
            if(state.isShipAtThisCoordinate()) {
                c = state.getCoordinate();
                break;
            }
        }
        return c;
    }

    @Test
    public void sizeOfBoardStateShouldBe100() {
        assertEquals(100, board.getBoardState().size());
    }

    @Test
    public void shootTest() {
        Coordinate c = getShipPosition(board);
        board.coordinateHit(c);
        System.out.println(board.getBoardState());
    }

    @Test
    public void testGame() {
        Game game = new Game();
        Board board = game.getBoard();
        Coordinate c = getShipPosition(board);
        game.shoot(c);
        System.out.println(board.getBoardState());
    }

}
