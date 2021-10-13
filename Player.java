public abstract class Player {

    private CharToken playerToken;

    public Player(CharToken playerToken){
        this.playerToken = playerToken;
    }

    public CharToken getPlayerToken() {
        return this.playerToken;
    }


    public abstract int getMove();


}
