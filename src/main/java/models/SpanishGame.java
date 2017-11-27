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
        Card c1 = getTopCard(column);
        // If card at column is a Joker, we remove both if there is another card that is a Joker
        if (c1.suit == Suit.Jokers) {
            for (int i = 0; i < 4; ++i) {
                if (i != column && !isColumnEmpty(i)) {
                    Card c2 = getTopCard(i);
                    if (c2.suit == Suit.Jokers) {
                        super.remove(column);
                        super.remove(i);
                        this.status = "Removed two Jokers";
                    }
                }
            }
        }
        // If card at column is not a Joker, and there is another card that is either of the same suit or is a Joker, we remove the card.
        else if (isCardRemovable(column)) {
            super.remove(column);
            this.status = c1.toString() + " (Removed)";
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
                        (c2.getSuit() == Suit.Jokers && c2.getValue() >= c1.getValue()))
                    return true;
            }
        }
        return false;
    }
}
