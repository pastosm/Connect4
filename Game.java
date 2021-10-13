import java.util.ArrayList;

public class Game {

    // Private instance variables
    private ArrayList<Player> players;
    private Board board;
    private View view;
    private int currPlayer = 0;

    // Private final variables
    private final int ROW_SIZE;
    private final int COL_SIZE;
    private final int CONNECT_N;

    // Constructor
    public Game(int ROW_SIZE, int COL_SIZE, int CONNECT_N){
        this.ROW_SIZE = ROW_SIZE;
        this.COL_SIZE = COL_SIZE;
        this.CONNECT_N = CONNECT_N;
        initGame();

    }

    // Initialize game
    private void initGame(){

        //Create our users list and add the players to it
        players = new ArrayList<Player>();
        players.add(new HumanPlayer(new CharToken('r')));
        players.add(new ComputerPlayer(new CharToken('y'), COL_SIZE));

        //Create our game board.
        board = new Board(ROW_SIZE, COL_SIZE, CONNECT_N);

        //Create our view instance
        view = new CLIView();
    }

    // Method that starts the game
    public void playGame() {
        displayWelcomeMsg();
        view.display(board.toString());

        // Loop until the game finds a winner or the board is full
        while (true) {
            // playerNumber converts indexes starting from 1 (instead of 0)
            int playerNumber = currPlayer + 1;
            displayCurrTurn(playerNumber);
            Player usr = players.get(currPlayer);
            int move = usr.getMove();

            // If token can be placed, place it
            if (board.canPlace(move)) {
                board.placeToken(usr.getPlayerToken(), move);
                view.display(board.toString());
            }

            // If the board has a winner after a move has been placed, then the winner is the current player
            if (board.hasWinner(usr.getPlayerToken())) {
                System.out.println("Player " + playerNumber + " (" + usr.getPlayerToken().getTokenToString() + ") has won!");
                break;
            }

            // If the board is full, end the game with no winners.
            else if (board.isFull()) {
                System.out.println("The board is full. The game has ended with no winners.");
                break;
            }

            // Increment the players turn
            currPlayer = (currPlayer +1) % players.size();

        }

    }

    // Helper method for displaying the players in the game in string format.
    private String getPlayersToString() {
        ArrayList<String> playersStrList = new ArrayList<>();
        for (int i = 0; i < this.players.size(); i++) {
            int playerNumber = i + 1;
            // Get the token name representation from a token (e.g "r" is "Red")
            String tokenName = this.players.get(i).getPlayerToken().getTokenToString();
            //If player is Computer, add (CPU) in the message
            if (this.players.get(i) instanceof ComputerPlayer) {
                playersStrList.add("Player " + playerNumber + " is " + tokenName + " (CPU)");
            }
            else {
                playersStrList.add("Player " + playerNumber + " is " + tokenName);
            }
        }
        String playerString = playersStrList.toString();
        return playerString.substring(1, playerString.length() - 1);
    }


    // Method that displays which player's turn it is
    private void displayCurrTurn(int playerNumber) {
        //If player is Computer, add (CPU) in the message
        if (players.get(currPlayer) instanceof ComputerPlayer) {
            System.out.println("It is Player " + playerNumber + " turn (CPU).");
        }
        else {
            System.out.println("It is Player " + playerNumber + " turn.");
        }
    }

    // Method that displays the welcome message
    private void displayWelcomeMsg() {
        System.out.println("Welcome to Connect 4");
        System.out.println("There are " + players.size() + " players, " + getColorsToString());
        System.out.println(getPlayersToString());
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
        System.out.println("");
    }

    // Helper method for returning all the colors of the player's list in a string format
    // E.g Red, Yellow and Blue
    private String getColorsToString() {
        String colorsStr = "";
        for (int i = 0; i < this.players.size(); i++) {
            // Get the token name representation from a token (e.g "r" is "Red")
            String tokenName = this.players.get(i).getPlayerToken().getTokenToString();
            if (i == this.players.size() - 1) {
                colorsStr += "and " + tokenName;
            }
            else if (i == this.players.size() - 2){
                colorsStr += tokenName + " ";
            }
            else {
                colorsStr += tokenName + ", ";
            }

        }
        return colorsStr;
    }




}
