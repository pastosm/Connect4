import java.util.Random;

public class ComputerPlayer extends Player {

    // Private instance variables
    private Random rnd;
    private int colSize;

    // Constructor
    public ComputerPlayer(CharToken token, int colSize) {
        super(token);
        this.colSize = colSize;
        this.rnd = new Random();
    }

    @Override
    public int getMove() {
        // Making a random move
        int move = rnd.nextInt(colSize);
        System.out.println("Making move on column " + (move+1) + ".");
        return move;

    }



}
