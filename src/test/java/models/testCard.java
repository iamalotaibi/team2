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
        Card c1 = new Card(5, Suit.Swords, Card.getHtmlString(5, Suit.Swords));
        assertEquals("5 Swords", c1.toString());

        Card c2 = new Card(13, Suit.Hearts, Card.getHtmlString(13, Suit.Hearts));
        assertEquals("K \u2665", c2.toString());

        Card c3 = new Card(14, Suit.Spades, Card.getHtmlString(14, Suit.Spades));
        assertEquals("A \u2660", c3.toString());

        Card c4 = new Card(5, Suit.Diamonds, Card.getHtmlString(5, Suit.Diamonds));
        assertEquals("5 \u2666", c4.toString());

        Card c5 = new Card( 11, Suit.Cups, Card.getHtmlString(11, Suit.Cups));
        assertEquals("J Cups", c5.toString());
    }
}
