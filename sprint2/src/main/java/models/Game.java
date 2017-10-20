package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Assignment 1: Each of the blank methods below require implementation to get AcesUp to build/run
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>(4);


    public Game(){
        // initialize a new game such that each column can store cards
        for (int i = 0; i < 4; ++i) {
            cols.add(new ArrayList<Card>(13));
        }
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

        // Create a copy of the original deck
        ArrayList<Card> deck2 = new ArrayList<>(this.deck);
        int deck_size = this.deck.size();

        // Randomize
        for (int i = 0, j = deck_size; i < deck_size; ++i, --j) {
            int rand_index = (int)(Math.random() * j);
            this.deck.set(i, deck2.get(rand_index));
            deck2.remove(rand_index);
        }
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        int deck_size = deck.size();
        if (deck_size >= 4) {
            for (int i = 0; i < 4; ++i) {
                int last_index = deck_size - 1 - i;
                Card removed_card = deck.remove(last_index);
                this.addCardToCol(i, removed_card);
            }
            System.out.println("Cards left in deck: " + deck.size());
            int end_state = hasGameBeenWon();
        }
    }

    public void remove(int columnNumber) {
        // remove the top card from the indicated column

        if (columnNumber > 3 || columnNumber < 0 || !(columnHasCards(columnNumber))) {
            System.out.println("Cannot remove from Column " + columnNumber );
            return;
        }
        Card targetCard = getTopCard(columnNumber);
        if(columnNumber != 0 && targetCard.suit == getTopCard(0).suit && targetCard.value < getTopCard(0).value)
            this.removeCardFromCol(columnNumber);
        else if(columnNumber != 1 && targetCard.suit == getTopCard(1).suit && targetCard.value < getTopCard(1).value)
            this.removeCardFromCol(columnNumber);
        else if(columnNumber != 2 && targetCard.suit == getTopCard(2).suit && targetCard.value < getTopCard(2).value)
            this.removeCardFromCol(columnNumber);
        else if(columnNumber != 3 && targetCard.suit == getTopCard(3).suit && targetCard.value < getTopCard(3).value)
            this.removeCardFromCol(columnNumber);
        else {
            System.out.println("Cannot remove from Column " + columnNumber );
            return;
        }
        System.out.println("Removed from Column " + columnNumber );

        int end_state = hasGameBeenWon();
    }

    private boolean columnHasCards(int columnNumber) {
        // check indicated column for number of cards; if no cards return false, otherwise return true
        if (this.cols.get(columnNumber).size() == 0)
            return false;
        else
            return true;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }


    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        if (columnHasCards(columnFrom) == false)
            System.out.println("You can't, (" + columnFrom + ") is empty.");
        else if (columnHasCards(columnTo) == true)
            System.out.println("You can't, no empty space in column (" + columnTo + ").");
        else {
            this.addCardToCol(columnTo, getTopCard(columnFrom));
            this.removeCardFromCol(columnFrom);
            System.out.println("Moved: from (" + columnFrom + "), to (" + columnTo + ").");
        }

        int end_state = hasGameBeenWon();
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }

    // returns true if there are no cards left if the deck,
    // no cards can be moves, and no cards can be removed
    private boolean inEndState() {
        // deck size must be zero to be in an end state
        if (deck.size() != 0) {
            return false;
        }
        // check if any cards can be moved
        if (cols.get(0).size() == 0 || cols.get(1).size() == 0 || cols.get(2).size() == 0 || cols.get(3).size() == 0) {
            return false;
        }
        // check if cards can be removed from any column
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) {
                    continue;
                }
                Card targetCard = getTopCard(i);
                if (j != 0 && targetCard.suit == getTopCard(0).suit && targetCard.value < getTopCard(0).value) {
                    return false;
                } else if (j != 1 && targetCard.suit == getTopCard(1).suit && targetCard.value < getTopCard(1).value) {
                    return false;
                } else if (j != 2 && targetCard.suit == getTopCard(2).suit && targetCard.value < getTopCard(2).value) {
                    return false;
                } else if (j != 3 && targetCard.suit == getTopCard(3).suit && targetCard.value < getTopCard(3).value) {
                    return false;
                }
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
        int cards_left = cols.get(0).size() + cols.get(1).size() + cols.get(2).size() + cols.get(3).size();
        boolean end_state = inEndState();
        // The only way cards_left could equal 4 is if the 4 aces are left
        if (end_state && cards_left == 4) {
            System.out.println("The game is over!");
            System.out.println("You win!");
            return 1;
        } else if (end_state) {
            System.out.println("The game is over!");
            System.out.println("You lose!");
            System.out.println("Score: " + getScore());
            return -1;
        } else {
            return 0;
        }
    }

    private int getScore() {
        int cards_left = cols.get(0).size() + cols.get(1).size() + cols.get(2).size() + cols.get(3).size();
        return (52 - cards_left);
    }
}