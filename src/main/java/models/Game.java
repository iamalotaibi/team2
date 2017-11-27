package models;

public abstract class Game extends Board {

    public Integer score = new Integer(0);
    public String status = new String("");

    // Stores the state of the game
    //    0 if moves can still be made,
    //    1 if the game has been won,
    //   -1 if the game has been lost.
    public int end_state;

    public Game() {
        end_state = 0;
    }

    public void dealFour() {
        super.dealFour();
        end_state = hasGameBeenWon();
    }

    // checks if card at columnNumber can be moved according to game rules
    // if it can, call super.move(columnFrom, columnTo)
    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        if (isCardMovable(columnFrom)) {
            super.move(columnFrom, columnTo);
            System.out.println("Moved: from (" + columnFrom + "), to (" + columnTo + ").");
        }

        end_state = hasGameBeenWon();
    }

    // checks if card at columnNumber can be removed according to game rules
    // if it can, call super.remove(columnNumber)
    public void remove(int columnNumber) {
        super.remove(columnNumber);
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

    // returns 1 if the game has been won,
    //        -1 if the game has been lost,
    //         0 if moves can still be made.
    public int hasGameBeenWon() {
        int cards_left = this.cols.get(0).size() + this.cols.get(1).size() + this.cols.get(2).size() + this.cols.get(3).size();
        boolean bend_state = inEndState();
        // The only way cards_left could equal 4 is if the 4 aces are left
        if (bend_state && cards_left == 4) {
            this.status = "Game over! You win!";
            System.out.println("The game is over!");
            System.out.println("You win!");
            return 1;
        } else if (bend_state) {
            this.status = "Game over! You lose!";
            System.out.println("The game is over!");
            System.out.println("You lose!");
            System.out.println("Score: " + getScore());
            return -1;
        } else {
            return 0;
        }
    }

    public int getScore() {
        return 0;
    }

    // returns true if card at column can be moved
    public boolean isCardMovable(int column) {
        // Validate
        if (column < 0 || column > 3 || isColumnEmpty(column) || getTopCard(column).value != 14)
            return false;
        // Test if card can be moved
        for (int i = 0; i < 4; ++i) {
            if (i != column && isColumnEmpty(i))
                return true;
        }
        return false;
    }

    // returns true if card at column can be removed
    public boolean isCardRemovable(int column) {
        return false;
    }
}
