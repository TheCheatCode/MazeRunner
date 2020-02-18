import java.util.Scanner;

public class MazeRunner {
    public static void main(String[] args) {
        Maze myMap = new Maze();
        Scanner in = new Scanner(System.in);

        intro(myMap);

        userMove(myMap, in);
    }

    private static void intro(Maze myMap) {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");

        myMap.printMap();
    }

    private static void userMove(Maze myMap, Scanner in) {
        boolean validInput = false;

        while (!validInput) {
            char userDirection = getUserDirection(in);

            switch (userDirection) {
                case 'R':   validInput = true;
                            if (myMap.canIMoveRight()) {
                                myMap.moveRight();
                            } else {
                                System.out.println("Sorry, you've hit a wall");
                            }
                            break;
                case 'L':   validInput = true;
                            if (myMap.canIMoveLeft()) {
                                myMap.moveLeft();
                            } else {
                                System.out.println("Sorry, you've hit a wall");
                            }
                            break;
                case 'U':   validInput = true;
                            if (myMap.canIMoveUp()) {
                                myMap.moveUp();
                            } else {
                                System.out.println("Sorry, you've hit a wall");
                            }
                            break;
                case 'D':   validInput = true;
                            if (myMap.canIMoveDown()) {
                                myMap.moveDown();
                            } else {
                                System.out.println("Sorry, you've hit a wall");
                            }
                            break;
                default:    break;
            }
        }
        myMap.printMap();
    }

    private static char getUserDirection(Scanner in) {
        System.out.print("Where would you like to move?");
        String userInput = in.nextLine().toUpperCase();
        System.out.println();

        if (userInput.length() > 0) {
            return userInput.charAt(0);
        }
        return ' ';
    }
}