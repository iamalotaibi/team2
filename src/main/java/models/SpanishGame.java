package models;

public class SpanishGame extends Game {

    public SpanishGame() {
        deck = new SpanishDeck();
    }

    public int getScore() {
        this.score = 50 - (this.cols.get(0).size() +
                this.cols.get(1).size() +
                this.cols.get(2).size() +
                this.cols.get(3).size() +
                this.deck.size());
        return this.score;
    }

    // checks if card at columnNumber can be removed according to game rules
    // if it can, call super.remove(columnNumber)
    public void remove(int column) {
        // Validate
        if (column < 0 || column > 3 || isColumnEmpty(column))
            return;
        // The card at the column is removed if a card at a different column is of the same suit and a higher value.
        Card c1 = getTopCard(column);
        for (int i = 0; i < 4; ++i) {
            if (i != column && !isColumnEmpty(i)) {
                Card c2 = getTopCard(i);
                if (c2.getSuit() == c1.getSuit() && c2.getValue() > c1.getValue()) {
                    super.remove(column);
                    this.status = c1.toString() + " (Removed)";
                    end_state = hasGameBeenWon();
                    return;
                }
            }
        }
        // The card at the column is removed if a card at a different column is a Joker, in which case the Joker is also removed.
        for (int i = 0; i < 4; ++i) {
            if (i != column && !isColumnEmpty(i)) {
                Card c2 = getTopCard(i);
                if (c2.getSuit() == Suit.Jokers) {
                    super.remove(column);
                    super.remove(i);
                    this.status = c1.toString() + " and " + c2.toString() + " (Removed)";
                    end_state = hasGameBeenWon();
                    return;
                }
            }
        }
    }

    // returns true if card at column can be removed
    public boolean isCardRemovable(int column) {
        // Validate
        if (column < 0 || column > 3 || isColumnEmpty(column))
            return false;
        // Test if card can be removed
        Card c1 = getTopCard(column);
        for (int i = 0; i < 4; ++i) {
            if (i != column && !isColumnEmpty(i)) {
                Card c2 = getTopCard(i);
                if ((c2.getSuit() == c1.getSuit() && c2.getValue() > c1.getValue()) ||
                        (c2.getSuit() == Suit.Jokers))
                    return true;
            }
        }
        return false;
    }
}
