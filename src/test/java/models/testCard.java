package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class testCard {

    @Test
    public void testGetSuit(){
        Card c = new Card(5, Suit.Clubs);
        assertEquals(Suit.Clubs, c.getSuit());
    }

    @Test
    public void testToString(){
        Card c1 = new Card(5, Suit.Clubs);
        assertEquals("5 \u2663", c1.toString());

        Card c2 = new Card(5, Suit.Hearts);
        assertEquals("5 \u2665", c2.toString());

        Card c3 = new Card(5, Suit.Spades);
        assertEquals("5 \u2660", c3.toString());

        Card c4 = new Card(5, Suit.Diamonds);
        assertEquals("5 \u2666", c4.toString());
    }
}
