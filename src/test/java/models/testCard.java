package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class testCard {

    @Test
    public void testGetSuit(){
        Card c = new Card(5, Suit.Clubs, Card.getHtmlString(5, Suit.Clubs));
        assertEquals(Suit.Clubs, c.getSuit());
    }

    @Test
    public void testToString(){
        Card c1 = new Card(11, Suit.Clubs, Card.getHtmlString(11, Suit.Clubs));
        assertEquals("J \u2663", c1.toString());

        Card c2 = new Card(12, Suit.Hearts, Card.getHtmlString(12, Suit.Hearts));
        assertEquals("Q \u2665", c2.toString());

        Card c3 = new Card(13, Suit.Spades, Card.getHtmlString(13, Suit.Spades));
        assertEquals("K \u2660", c3.toString());

        Card c4 = new Card(14, Suit.Diamonds, Card.getHtmlString(14, Suit.Diamonds));
        assertEquals("A \u2666", c4.toString());

        Card c5 = new Card(5, Suit.Coins, Card.getHtmlString(5, Suit.Coins));
        assertEquals("5 Coins", c5.toString());

        Card c6 = new Card(5, Suit.Cups, Card.getHtmlString(5, Suit.Cups));
        assertEquals("5 Cups", c6.toString());

        Card c7 = new Card(5, Suit.Swords, Card.getHtmlString(5, Suit.Swords));
        assertEquals("5 Swords", c7.toString());

        Card c8 = new Card(0, Suit.Jokers, Card.getHtmlString(0, Suit.Jokers));
        assertEquals("0 Jokers", c8.toString());

    }
}
