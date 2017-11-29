package models;

public class SpanishDeck extends Deck {

    public SpanishDeck() {
        super();
        for(int i = 2; i < 14; i++)
            addCardofEachSuit(i);
        this.deck.add(new Card(14,Suit.Jokers,Card.getHtmlString(14,Suit.Jokers)));
        this.deck.add(new Card(14,Suit.Jokers,Card.getHtmlString(14,Suit.Jokers)));
        super.shuffle();
    }

    private void addCardofEachSuit(int value) {
        this.deck.add(new Card(value,Suit.Clubs,Card.getHtmlString(value,Suit.Clubs)));
        this.deck.add(new Card(value,Suit.Cups,Card.getHtmlString(value,Suit.Cups)));
        this.deck.add(new Card(value,Suit.Coins,Card.getHtmlString(value,Suit.Coins)));
        this.deck.add(new Card(value,Suit.Swords,Card.getHtmlString(value,Suit.Swords)));
    }
}
