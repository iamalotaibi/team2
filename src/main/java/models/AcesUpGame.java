package models;

public class AcesUpGame extends Game {

    public AcesUpGame() {
        deck = new AcesUpDeck();
    }

    public int getScore() {
        this.score = 52 - (this.cols.get(0).size() +
                this.cols.get(1).size() +
                this.cols.get(2).size() +
                this.cols.get(3).size() +
                this.deck.size());
        return this.score;
    }

    // checks if card at columnNumber can be removed according to game rules
    // if it can, call super.remove(columnNumber)
    public void remove(int columnNumber) {
        // Remove the top card from the indicated column
        if (isCardRemovable(columnNumber)) {
            Card rem_card = getTopCard(columnNumber);
            super.remove(columnNumber);
            this.status = rem_card.toString() + " (Removed)";
            System.out.println("Removed from column " + columnNumber );

            end_state = hasGameBeenWon();
        } else {
            System.out.println("Cannot remove from column " + columnNumber );
        }
    }

    // returns true if card at column can be removed
    public boolean isCardRemovable(int column) {
        // Validate
        if (column < 0 || column > 3 || isColumnEmpty(column))
            return false;
        // Test if card can be removed
        Card c = getTopCard(column);
        for (int i = 0; i < 4; ++i) {
            if (i != column && !isColumnEmpty(i)) {
                Card compare = getTopCard(i);
                if (compare.getSuit() == c.getSuit() && compare.getValue() > c.getValue())
                    return true;
            }
        }
        return false;
    }
}
