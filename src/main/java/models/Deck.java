package models;

import java.util.ArrayList;

public class Deck {
    public java.util.List<Card> deck = new ArrayList<>();

    // generates and shuffles deck
    public Deck() {
        deck = new ArrayList<>();
        for(int i = 2; i < 15; i++){
            this.deck.add(new Card(i,Suit.Clubs));
            this.deck.add(new Card(i,Suit.Hearts));
            this.deck.add(new Card(i,Suit.Diamonds));
            this.deck.add(new Card(i,Suit.Spades));
        }
        shuffle();
    }

    // return the next card in the deck
    // if no cards left in deck, return null
    public Card draw() {
        if (size() > 0) {
            Card next_card = deck.get(size() - 1);
            deck.remove(size() - 1);
            return next_card;
        } else {
            return null;
        }
    }

    public int size() {
        return deck.size();
    }

    // shuffles the deck so that it is random
    private void shuffle() {
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
}
