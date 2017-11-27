package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public java.util.List<java.util.List<Card>> cols;
    public Deck deck;

    // initialize board, create and shuffle deck
    public Board() {
        cols = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            this.cols.add(new ArrayList<Card>());
        }
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column
        // repeat for each of the four columns
        for (int i = 0; i < 4; ++i) {
			if (deck.size() > 0)
				addCardToCol(i, deck.draw());
		}
    }

    public Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size() - 1);
    }

    public boolean isColumnEmpty(int columnNumber) {
        return this.cols.get(columnNumber).size() == 0;
    }

    public boolean columnHasCards(int columnNumber) {
        return this.cols.get(columnNumber).size() != 0;
    }

    // remove the top card from the columnFrom column, add it to the columnTo column
    protected void move(int columnFrom, int columnTo) {
        if (isColumnEmpty(columnFrom)) {
            System.out.println("You can't, (" + columnFrom + ") is empty.");
        } else if (columnHasCards(columnTo)) {
            System.out.println("You can't, no empty space in column (" + columnTo + ").");
        } else {
            this.addCardToCol(columnTo, getTopCard(columnFrom));
            this.removeCardFromCol(columnFrom);
            System.out.println("Moved: from (" + columnFrom + "), to (" + columnTo + ").");
        }
    }

    // Remove the top card from the indicated column
    protected void remove(int columnNumber) {
        if (columnNumber > 3 || columnNumber < 0 || isColumnEmpty(columnNumber)) {
            System.out.println("Cannot remove from column " + columnNumber );
            if (isColumnEmpty(columnNumber)) {
                System.out.println("Column (" + columnNumber + ") is empty");
            }
        } else {
            this.removeCardFromCol(columnNumber);
        }
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        this.cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }
}

