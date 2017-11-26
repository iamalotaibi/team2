package models;

public class SpanishDeck extends Deck {

    public SpanishDeck() {
        super();
        for(int i = 2; i < 14; i++)
            addCardofEachSuit(i);
        this.deck.add(new Card(14,Suit.Jokers));
        this.deck.add(new Card(14,Suit.Jokers));
        super.shuffle();
    }

    private void addCardofEachSuit(int value) {
        this.deck.add(new Card(value,Suit.Clubs));
        this.deck.add(new Card(value,Suit.Cups));
        this.deck.add(new Card(value,Suit.Coins));
        this.deck.add(new Card(value,Suit.Swords));
    }
}
