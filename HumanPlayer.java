import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

    // Private instance variables
    private BufferedReader input;

    // Constructor
    public HumanPlayer(CharToken token) {
        super(token);
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public int getMove() {
        int move = 0;
        // Loop until user enters valid integer
        while(true) {
            String userInput = getPlayerInput();
            if (isInteger(userInput)) {
                move = Integer.parseInt(userInput) - 1;
                break;
            }
            else {
                System.out.println("Please enter an integer.");
            }
        }
        return move;


    }

    // Helper method for reading user input
    private String getPlayerInput() {
        String toReturn = null;
        try {
            toReturn = input.readLine();
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return toReturn;
    }

    // Helper method for validating if user input is an integer
    private boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }

    }
}
