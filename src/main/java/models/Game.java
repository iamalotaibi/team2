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

    public Boolean card_selected = false;
    public Integer card_selected_rc[] = new Integer[2];
    public Integer top_card_modes[] = new Integer[4];
    public Integer top_card_rows[] = new Integer[4];

    public Integer score = new Integer(0);
    public String status = new String("");

    public Game(){
        // initialize a new game such that each column can store cards
        for (int i = 0; i < 4; ++i) {
            this.cols.add(new ArrayList<Card>(13));
            this.top_card_modes[i] = 0;
            this.top_card_rows[i] = 0;
        }
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            this.deck.add(new Card(i,Suit.Clubs));
            this.deck.add(new Card(i,Suit.Hearts));
            this.deck.add(new Card(i,Suit.Diamonds));
            this.deck.add(new Card(i,Suit.Spades));
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
        int deck_size = this.deck.size();
        if (deck_size >= 4) {
            for (int i = 0; i < 4; ++i) {
                int last_index = deck_size - 1 - i;
                Card removed_card = this.deck.remove(last_index);
                this.addCardToCol(i, removed_card);
            }
            System.out.println("Cards left in deck: " + this.deck.size());
            int end_state = hasGameBeenWon();
        }
    }

    public void remove(int columnNumber) {
        // Remove the top card from the indicated column
        if (columnNumber > 3 || columnNumber < 0 || !(columnHasCards(columnNumber))) {

            System.out.println("Cannot remove from Column " + columnNumber );
            return;
        }
        // Use remove function from instructor-posted sprint1
        Card c = getTopCard(columnNumber);
        boolean removeCard = false;
        for (int i = 0; i < 4; i++) {
            if (i != columnNumber) {
                if (columnHasCards(i)) {
                    Card compare = getTopCard(i);
                    if (compare.getSuit() == c.getSuit()) {
                        if (compare.getValue() > c.getValue()) {
                            removeCard = true;
                        }
                    }
                }
            }
        }
        if (removeCard) {
            Card rem_card = this.cols.get(columnNumber).get(this.cols.get(columnNumber).size() - 1);
            this.status = getCardValueString(rem_card) + " (Removed)";
            this.cols.get(columnNumber).remove(this.cols.get(columnNumber).size() - 1);
            System.out.println("Removed from Column " + columnNumber );
            int end_state = hasGameBeenWon();
        }
        else {
            System.out.println("Cannot remove from Column " + columnNumber );
        }
    }

    private boolean columnHasCards(int columnNumber) {
        // check indicated column for number of cards; if no cards return false, otherwise return true
        if (this.cols.get(columnNumber).size() == 0)
            return false;
        else
            return true;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size() - 1);
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
        this.cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }

    // returns true if there are no cards left if the deck,
    // no cards can be moves, and no cards can be removed
    private boolean inEndState() {
        // deck size must be zero to be in an end state
        if (this.deck.size() != 0) {
            return false;
        }
        // check if any cards can be moved
        if (this.cols.get(0).size() == 0 || this.cols.get(1).size() == 0 || this.cols.get(2).size() == 0 || this.cols.get(3).size() == 0) {
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

    public boolean isCardRemovable(int column) {
        // Validate
        if (column < 0 || column > 3 || !columnHasCards(column))
            return false;
        // Test if card can be removed
        Card c = getTopCard(column);
        for (int i = 0; i < 4; ++i) {
            if (i != column && columnHasCards(i)) {
                Card compare = getTopCard(i);
                if (compare.getSuit() == c.getSuit() && compare.getValue() > c.getValue())
                    return true;
            }
        }
        return false;
    }

    public boolean isCardMovable(int column) {
        // Validate
        if (column < 0 || column > 3 || !columnHasCards(column))
            return false;
        // Test if card can be moved
        for (int i = 0; i < 4; ++i) {
            if (i != column && !columnHasCards(i))
                return true;
        }
        return false;
    }

    // This is triggered whenever user selects a card.
    // Returns
    //  * 0 if selection is invalid.
    //  * 1 if a card is removed.
    //  * 2 if a card is selected.
    //  * 3 if originally selected card is moved to another location.
    public int processCardSelection(int col, int row) {
        // Validate col
        if (col < 0 || col > 3) {
            this.card_selected = false;
            System.out.println("Selected column invalid!");
            return 0;
        }
        // Obtain top empty row at a column
        int top_empty_row = this.cols.get(col).size();
        // Validate row
        if (row < top_empty_row - 1 || row > top_empty_row) {
            this.card_selected = false;
            System.out.println("Selected row invalid!");
            return 0;
        }
        // Validate selected card (just in case)
        if (this.card_selected && (this.card_selected_rc[1] != this.cols.get(this.card_selected_rc[0]).size() - 1 || !isCardMovable(this.card_selected_rc[0]))) {
            this.card_selected = false;
            System.out.println("Original selection determined invalid and cleared!");
        }

        // Performing selection
        // If a card is already selected,
        //   - the selected card could be moved to a new location
        //   - a different selection could be made
        // If there is no selection yet,
        //  - selection could be made
        // When a selection is made,
        //  - if the location contains a card
        //      - if the card can be removed, it will be removed
        //      - if the card can be moved, it will be selected
        //  - if the location is empty/invalid
        //      - current selection is cleared

        // Move card if selection is valid
        System.out.println("Card selected: " + String.valueOf(this.card_selected));
        if (this.card_selected && col != this.card_selected_rc[0] && !columnHasCards(col) && row == 0) {
            move(this.card_selected_rc[0], col);
            this.card_selected = false;
            System.out.println("Selected card moved!");
            return 3;
        }
        // Otherwise perform a new selection if valid
        else if (row == top_empty_row - 1) {
            if (isCardRemovable(col)) {
                remove(col);
                this.card_selected = false;
                System.out.println("Card at location removed!");
                return 1;
            }
            else if (isCardMovable(col)) {
                this.card_selected_rc[0] = col;
                this.card_selected_rc[1] = row;
                this.card_selected = true;
                System.out.println("Card at location selected!");
                return 2;
            }
        }
        // If all fails, clear selection
        this.card_selected = false;
        System.out.println("Selection cleared/invalid!");
        return 0;
    }

    // Call this every time dealing, moving, or removing.
    public void clearCardSelection() {
        this.card_selected = false;
    }

    // Call this every time dealing, moving, or removing.
    public void updateTopCardModes() {
        // Modes: 0 - default; 1 - movable; 2 - removable; 3 - selected; 4 - destination
        if (this.card_selected) {
            this.top_card_modes[this.card_selected_rc[0]] = 3;
            for (int i = 0; i < 4; ++i) {
                this.top_card_rows[i] = this.cols.get(i).size() - 1;
                if (i != this.card_selected_rc[0]) {
                    if (this.top_card_rows[i] == -1) {
                        this.top_card_modes[i] = 4;
                        this.top_card_rows[i] = 0;
                    }
                    else if (this.isCardRemovable(i))
                        this.top_card_modes[i] = 2;
                    else if (this.isCardMovable(i))
                        this.top_card_modes[i] = 1;
                    else
                        this.top_card_modes[i] = 0;
                }
            }
        }
        else {
            for (int i = 0; i < 4; ++i) {
                this.top_card_rows[i] = this.cols.get(i).size() - 1;
                if (this.isCardRemovable(i))
                    this.top_card_modes[i] = 2;
                else if (this.isCardMovable(i))
                    this.top_card_modes[i] = 1;
                else
                    this.top_card_modes[i] = 0;
            }
        }
    }
}
