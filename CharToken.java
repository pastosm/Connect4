import java.util.HashMap;

public class CharToken implements Token {

    // Private instance variables
    private char token;
    private boolean empty;
    private HashMap<Character, String> colors = new HashMap<Character, String>();

    // Constructor
    public CharToken(char token) {
        this.token = token;
        this.empty = false;
        populateColorsMap();
    }

    // Constructor for empty tokens
    public CharToken(){
        this.empty = true;
    }

    // Getters and Setters
    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    // HashMap which is used to get the long version of a char color
    private void populateColorsMap() {
        colors.put('r', "Red");
        colors.put('b', "Blue");
        colors.put('y', "Yellow");
        colors.put('g', "Green");
    }

    // Interface implemented methods

    @Override
    public void setEmptyToken() {
        this.empty = true;
    }

    @Override
    public boolean isEmpty() {
        return this.empty;
    }

    @Override
    public String getTokenToString() {
        return colors.get(token);
    }


}



