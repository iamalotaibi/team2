package models;

import models.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Assignment 1: Each of the blank methods below require implementation to get AcesUp to build/run
 */
public class Game extends Board {

    public Integer score = new Integer(0);
    public String status = new String("");

    public Game() { }

    public void buildGame() {
        buildBoard();
    }

    public void dealFour() {
        super.dealFour();
        int end_state = hasGameBeenWon();
    }

    public void remove(int columnNumber) {
        // Remove the top card from the indicated column
        if (isCardRemovable(columnNumber)) {
            Card rem_card = getTopCard(columnNumber);
            super.remove(columnNumber);
            this.status = getCardValueString(rem_card) + " (Removed)";
            System.out.println("Removed from Column " + columnNumber );
            int end_state = hasGameBeenWon();
        } else {
            System.out.println("Cannot remove from Column " + columnNumber );
        }
    }

    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        if (isCardMovable(columnFrom)) {
            super.move(columnFrom, columnTo);
            System.out.println("Moved: from (" + columnFrom + "), to (" + columnTo + ").");
        }

        int end_state = hasGameBeenWon();
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

    private String getCardValueString(Card current_card) {
        String str = "";
        switch (current_card.value) {
            case 11:
                str = "J ";
                break;
            case 12:
                str = "Q ";
                break;
            case 13:
                str = "K ";
                break;
            case 14:
                str = "A ";
                break;
            default:
                str = current_card.value + " ";
                break;
        }
        switch (current_card.suit) {
            case Hearts:
                str = str + "\u2665";
                break;
            case Spades:
                str = str + "\u2660";
                break;
            case Diamonds:
                str = str + "\u2666";
                break;
            case Clubs:
                str = str + "\u2663";
                break;
        }
        return str;
    }

    // returns 1 if the game has been won,
    //        -1 if the game has been lost,
    //         0 if moves can still be made.
    public int hasGameBeenWon() {
        int cards_left = this.cols.get(0).size() + this.cols.get(1).size() + this.cols.get(2).size() + this.cols.get(3).size();
        boolean end_state = inEndState();
        // The only way cards_left could equal 4 is if the 4 aces are left
        if (end_state && cards_left == 4) {
            this.status = "Game over! You win!";
            System.out.println("The game is over!");
            System.out.println("You win!");
            return 1;
        } else if (end_state) {
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
        this.score = 52 - (this.cols.get(0).size() +
                this.cols.get(1).size() +
                this.cols.get(2).size() +
                this.cols.get(3).size() +
                this.deck.size());
        return this.score;
    }

    public boolean isColumnEmpty(int column) {
        if (this.cols.get(column).size() == 0)
            return true;
        else
            return false;
    }

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
}
