package models;

public abstract class Game extends Board {

    public String status;
    public Integer score;

    // Stores the state of the game
    //    0 if moves can still be made,
    //    1 if the game has been won,
    //   -1 if the game has been lost.
    public Integer end_state;

    public Game() {
        status = new String();
        score = 0;
        end_state = 0;
    }

    public void dealFour() {
        super.dealFour();
        updateGameEndState();
    }

    // checks if card at columnNumber can be moved according to game rules
    // if it can, call super.move(columnFrom, columnTo)
    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        if (isCardMovable(columnFrom)) {
            super.move(columnFrom, columnTo);
            this.status = "Moved card from column " + String.valueOf(columnFrom) + " to column " + String.valueOf(columnTo);
        }
        updateGameEndState();
    }

    // checks if card at columnNumber can be removed according to game rules
    // if it can, call super.remove(column)
    public void remove(int column) {
        super.remove(column);
    }

    // returns true if there are no cards left if the deck,
    // no cards can be moves, and no cards can be removed
    private boolean inEndState() {
        // deck size must be zero to be in an end state
        if (this.deck.size() != 0) {
            return false;
        }
        // check if any cards can be moved or removed
        for(int i = 0; i < 4; i++) {
            if (isCardMovable(i) || isCardRemovable(i)) {
                return false;
            }
        }
        // if cards can't be moved or removed,
        // then the game is in an end state
        return true;
    }

    // Sets end_state to:
    //   1 if the game has been won,
    //  -1 if the game has been lost,
    //   0 if moves can still be made.
    protected void updateGameEndState() {
        int cards_left = this.cols.get(0).size() + this.cols.get(1).size() + this.cols.get(2).size() + this.cols.get(3).size();
        // The only way cards_left could equal 4 is if the 4 aces are left
        updateScore();
        if (inEndState()) {
            if (cards_left == 4) {
                this.status = "Game over! You win!";
                end_state = 1;
            }
            else {
                this.status = "Game over! You lose!";
                end_state = -1;
            }
        }
        else {
            end_state = 0;
        }
    }

    protected abstract void updateScore();

    // returns true if card at column can be moved
    public boolean isCardMovable(int column) {
        // Validate
        if (column < 0 || column > 3 || isColumnEmpty(column) || getTopCard(column).getValue() != 14)
            return false;
        // Test if card can be moved
        for (int i = 0; i < 4; ++i) {
            if (i != column && isColumnEmpty(i))
                return true;
        }
        return false;
    }

    // returns true if card at column can be removed
    public abstract boolean isCardRemovable(int column);

    // Returns the state of the game
    //    0 if moves can still be made,
    //    1 if the game has been won,
    //   -1 if the game has been lost.
    public int getGameEndState() {
        return end_state;
    }

    public int getScore() {
        return score;
    }
}
