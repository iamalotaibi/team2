package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public java.util.List<java.util.List<Card>> cols;
    public Deck deck;

    // initialize board, create and shuffle deck
    public Board() { }

    public void buildBoard() {
        cols = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            this.cols.add(new ArrayList<Card>());
        }

        deck = new Deck();
        deck.buildDeck();
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column
        // repeat for each of the four columns
        if (deck.size() == 0) { return; }
        for (int i = 0; i < 4; i++)
            addCardToCol(i, deck.draw());

    }

    public Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size() - 1);
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        this.cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }

    public boolean isColumnEmpty(int columnNumber) {
        return !columnHasCards(columnNumber);
    }

    public boolean columnHasCards(int columnNumber) {
        // check indicated column for number of cards; if no cards return false, otherwise return true
        return this.cols.get(columnNumber).size() != 0;
    }

    // remove the top card from the columnFrom column, add it to the columnTo column
    public void move(int columnFrom, int columnTo) {
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
    public void remove(int columnNumber) {
        if (columnNumber > 3 || columnNumber < 0 || !(columnHasCards(columnNumber))) {
            System.out.println("Cannot remove from Column " + columnNumber );
            if (!(columnHasCards(columnNumber))) {
                System.out.println("Column (" + columnNumber + ") is empty");
            }
        }
        this.cols.get(columnNumber).remove(this.cols.get(columnNumber).size() - 1);
    }

}

