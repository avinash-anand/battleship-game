package battleship;

import battleship.coordinate.Coordinate;
import battleship.game.Game;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        Game game = new Game();
        game.getBoard().printBoard();
        Scanner sc = new Scanner(System.in);
        String readLine = "";
        while (!readLine.equals("I LOSE")) {
            System.out.println("\n\nEnter a coordinate : ");
            readLine = sc.nextLine();
            if(readLine.equals("I LOSE")) {
                break;
            } else if(readLine.matches("[A-J]([1-9]|10)")) {
                Coordinate coordinate = getCoordinateOutOfInput(readLine);
                game.shoot(coordinate);
            } else {
                System.out.println("I don't understand your input");
            }
        }
        game.getBoard().printBoard();
    }

    private static Coordinate getCoordinateOutOfInput(String input) {
        int x = Integer.parseInt(input.substring(1));
        char y = input.charAt(0);
        return new Coordinate(x,y);
    }
}
