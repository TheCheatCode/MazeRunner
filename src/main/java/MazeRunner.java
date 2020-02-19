import java.util.Scanner;

public class MazeRunner {
    public static void main(String[] args) {
        Maze myMap = new Maze();
        Scanner in = new Scanner(System.in);

        boolean victory = false;
        int moves = 0;
        char previousDirection = 'R';

        intro(myMap);

        while (!victory && moves < 100) {
            previousDirection = userMove(myMap, in, previousDirection);
            moves++;

            victory = myMap.didIWin();
            if (!victory)
                moveMessages(moves);
        }

        if (victory) {
            System.out.println("Congratulations, you made it out alive!");
            System.out.println("and you did it in " + moves + " moves!");
        } else {
            System.out.println("Sorry, but you didn't escape in time- you lose!");
        }
    }

    private static void intro(Maze myMap) {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");

        myMap.printMap();
    }

    private static char userMove(Maze myMap, Scanner in, char previousDirection) {
        boolean validInput = false;
        String stringDirection = Character.toString(previousDirection);
        char userDirection = previousDirection;

        if (myMap.isThereAPit(stringDirection)) {
            validInput = navigatePit(myMap, in, stringDirection);
        }

        while (!validInput) {
            userDirection = getUserDirection(in);

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

        return userDirection;
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

    private static void moveMessages(int moves) {
        switch (moves) {
            case 50:    System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.");
                        break;
            case 75:    System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                        break;
            case 90:    System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                        break;
            case 100:   System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                        break;
            default:    break;
        }
    }

    private static boolean navigatePit(Maze myMap, Scanner in, String userDirection) {
        System.out.print("Watch out! There's a pit ahead, jump it?");
        String userInput = in.nextLine().toUpperCase();
        System.out.println();

        if (userInput.toUpperCase().startsWith("Y")) {
            myMap.jumpOverPit(userDirection.toUpperCase());
            return true;
        }

        return false;
    }
}
