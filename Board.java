
public class Board {

    // Private instance variables
    private CharToken[][] board;
    private int connectN;
    private int count;

    // Constructor
    public Board(int row, int col, int connectN) {
        this.board = new CharToken[row][col];
        this.connectN = connectN;
        populateEmptyBoard();
    }

    // Populates an empty board with empty CharTokens
    private void populateEmptyBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new CharToken();
            }
        }
    }

    // Reset our counter
    private void resetCount() {
        this.count = 0;
    }

    // Method that checks for horizontal win by passing in the player's token
    private boolean checkHorizontal(CharToken playerToken) {
        resetCount();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getToken() == playerToken.getToken()) {
                    count = count + 1;
                    if (count >= connectN) {
                        return true;
                    }
                }
                else {
                    resetCount();
                }
            }
            resetCount();
        }
        return false;
    }

    // Method that checks for vertical win by passing in the player's token
    private boolean checkVertical(CharToken playerToken) {
        resetCount();
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i].getToken() == playerToken.getToken()) {
                    count = count + 1;
                    if (count >= connectN) {
                        return true;
                    }
                }
                else {
                    resetCount();
                }
            }
            resetCount();
        }
        return false;
    }


    // Method that check if the board has any main diagonal winners ( / direction)
    public boolean checkMainDiagonal() {
        //N tokens in a row minus the current one on the board
        int nMinusCurrent = connectN - 1;
        // Iterate through the board up to board length minus nMinusCurrent
        // to avoid index out of bounds when checking for neighbors later
        for (int row = 0; row < board.length - nMinusCurrent; row++)
        {
            for (int col = 0; col < board[row].length - nMinusCurrent; col++)
            {
                boolean hasWon = true;
                // Get the current token and check if there are N tokens in a row
                CharToken token = board[row][col];
                // Check neighbors if they are the same token and are not empty tokens
                for (int i = 1; i <= nMinusCurrent; i++){
                    if (token == board[row + i][col + i] && !board[row + i][col + i].isEmpty()) {
                    }
                    // skip
                    else {
                        // If neighbor is not the same token, the current token has no nMinusCurrent neighbors in a row
                        hasWon = false;
                    }
                }
                // If we have finished the iteration with hasWon true, then we found a winner
                if (hasWon) {
                    return true;
                }
            }
        }
        // Iterated throughout the whole board and found no winner
        return false;
    }

    // Method that check if the board has any counter diagonal winners ( \ direction)
    public boolean checkCounterDiagonal() {
        //N markers in a row minus the current one on the board
        int nMinusCurrent = connectN - 1;
        // Iterate through the board up to board length minus nMinusCurrent
        // to avoid index out of bounds when checking for neighbors later
        for (int row = 0; row < board.length - nMinusCurrent; row++)
        {
            for (int col = nMinusCurrent; col < board[row].length; col++)
            {
                boolean hasWon = true;
                // Get the current token and check if there are N tokens in a row
                CharToken element = board[row][col];
                // Check neighbors if they are the same token and are not empty tokens
                for (int i = 1; i <= nMinusCurrent; i++) {
                    if (element == board[row + i][col - i] && !board[row + i][col - i].isEmpty()) {
                    }
                    //skip
                    else {
                        // If neighbor is not the same token, the current token has no nMinusCurrent neighbors in a row
                        hasWon = false;
                    }
                }
                // If we have finished the iteration with hasWon true, then we found a winner
                if (hasWon) {
                    return true;
                }
            }
        }
        // Iterated throughout the whole board and found no winner
        return false;
    }



    // Method that checks if the player has won either horizontally, vertically or diagonally
    public boolean hasWinner(CharToken userToken) {
        if (checkHorizontal(userToken)) {
            return true;
        }
        if (checkVertical(userToken)) {
            return true;
        }
        if (checkMainDiagonal()) {
            return true;
        }
        if (checkCounterDiagonal()) {
            return true;
        }
        return false;
    }


    // Places a token on the board given a token and a position
    public void placeToken(CharToken currUserToken, int position) {
        boolean placed = false;
        // Iterate through the board from the bottom up
        for (int i = board.length - 1; i >= 0; i--) {
            if (!placed) {
                // If current position is not empty and it's not the user's color
                if (!board[i][position].isEmpty() && board[i][position] != currUserToken) {
                    // skip to the next position
                }
                else if (board[i][position] != currUserToken) {
                    board[i][position] = currUserToken;
                    placed = true;
                }
            }
        }
    }


    // Method that checks if the user can place in the given position
    public boolean canPlace(int col) {
        // Check if column is in range
        if (col >= board[0].length || col < 0) {
            System.out.println("Cannot place. Position out of column range.");
            return false;
        }
        // Check for empty slot in the column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col].isEmpty()) {
                return true;
            }
        }
        System.out.println("Cannot place counter. Column is full.");
        return false;
    }

    // Method to check if the board is full
    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // As soon as we find an empty slot, return false
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        // Otherwise the board is full
        return true;
    }

    // Method that returns the board to String
    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!board[i][j].isEmpty()) {
                    toReturn.append("| ").append(board[i][j].getToken()).append(" ");
                }
                else {
                    toReturn.append("|   ");
                }
            }
            toReturn.append("|\n");
        }
        for (int k = 1; k <= board[0].length; k++) {
            toReturn.append("  ").append(k).append(" ");
        }
        toReturn.append("\n");
        return toReturn.toString();
    }


}
