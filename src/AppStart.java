import java.util.ArrayList;
import java.util.Scanner;

/**
 * AppStart
 */
public class AppStart {
    private static final Scanner _sc = new Scanner(System.in);
    public static void main(String[] args) {
        int option = -1;
        String name1;
        String name2;
        Game game = null;
        System.out.println("Welcome to the game of ***\"Bisca\"***");
        System.out.println("To play against another player press:    1");
        System.out.println("To play against a computer press:        2");
        System.out.println("To watch a game between computers press: 3");
        while (option < 1 || option > 3) {
            option = _sc.nextInt();
        }
        _sc.nextLine();
        switch (option) {
        case 1:
            System.out.print("Please enter player1 name: ");
            name1 = _sc.nextLine();
            System.out.print("Please enter player2 name: ");
            name2 = _sc.nextLine();
            game = new Game(new Human(name1), new Human(name2));
            break;
        case 2:
            System.out.print("Please enter your name: ");
            name1 = _sc.nextLine();
            name2 = "Loosing2Bot";
            game = new Game(new Human(name1), new Pc(name2));
            break;
        case 3:
            game = new Game(new Pc("Hal 9000"), new Pc("J.A.R.V.I.S"));
            break;
        default:
            break;
        }
        Player winner = game.play();
        System.out.println("The winner is :" + winner + " with " + winner.countPoints() + " points");
    }
}