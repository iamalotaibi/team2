package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Assignment 1: Each of the blank methods below require implementation to get AcesUp to build/run
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>(4);


    public Game(){
        // initialize a new game such that each column can store cards
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }
    }

    public void shuffle() {
        // shuffles the deck so that it is random
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
    }

    public void remove(int columnNumber) {
        // remove the top card from the indicated column
    }

    private boolean columnHasCards(int columnNumber) {
        // check indicated column for number of cards; if no cards return false, otherwise return true
        return false;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }

    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
    }

    // returns true if there are no cards left if the deck,
    // no cards can be moves, and no cards can be removed
    private boolean inEndState() {
        if (deck.size() == 0) {
            // check if cards can be moved from one column to another
            // check if cards can be removed
            // if both conditions are false, return true
        } else {
            return false;
        }
        return false;
    }

    // returns 1 if the game has been won,
    //        -1 if the game has been lost,
    //         0 if moves can still be made.
    public int hasGameBeenWon() {
        int cards_left = cols.get(0).size() + cols.get(1).size() + cols.get(2).size() + cols.get(3).size();
        // The only way cards_left could equal 4 is if the 4 aces are left
        if (inEndState() && cards_left == 4) {
            return 1;
        } else if (inEndState() && cards_left != 4) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getScore() {
        int cards_left = cols.get(0).size() + cols.get(1).size() + cols.get(2).size() + cols.get(3).size();
        return 52 - cards_left;
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }
}
