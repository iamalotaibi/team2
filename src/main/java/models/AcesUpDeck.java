package models;

import java.io.Serializable;

public class AcesUpDeck extends Deck {
    // generate 52 cards, 14 cards of each suit
    public AcesUpDeck() {
        super();
        for(int i = 2; i < 15; i++)
            addCardofEachSuit(i);
        super.shuffle();
    }

    private void addCardofEachSuit(int value) {
        this.deck.add(new Card(value,Suit.Clubs));
        this.deck.add(new Card(value,Suit.Hearts)); // Cups
        this.deck.add(new Card(value,Suit.Diamonds)); // Coins
        this.deck.add(new Card(value,Suit.Spades)); // Swords
    }
}
