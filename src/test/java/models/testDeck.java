package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class testDeck {

    @Test
    public void testBuildDeck(){
        Deck ad = new AcesUpDeck();
        assertEquals(52,ad.deck.size());

        Deck sd = new SpanishDeck();
        assertEquals(50,sd.deck.size());
    }

    @Test
    public void testDeckShuffle(){
        AcesUpDeck ad1 = new AcesUpDeck();
        AcesUpDeck ad2 = new AcesUpDeck();
        // ad1 and ad2 could shuffle to the same order, but that chance is approximately 1 in 8*10^67 shuffles
        assertFalse(Arrays.equals(ad1.deck.toArray(), ad2.deck.toArray()));

        SpanishDeck sd1 = new SpanishDeck();
        SpanishDeck sd2 = new SpanishDeck();
        // ad1 and ad2 could shuffle to the same order, but that chance is approximately 1 in 8*10^67 shuffles
        assertFalse(Arrays.equals(sd1.deck.toArray(), sd2.deck.toArray()));
    }

    @Test
    public void testEmptyDeck(){
        AcesUpDeck ad = new AcesUpDeck();
        for (int i = 0; i < 52; i++)
            ad.draw();
        assertEquals(0,ad.size());

        SpanishDeck sd = new SpanishDeck();
        for (int i = 0; i < 50; i++)
            sd.draw();
        assertEquals(0,sd.size());
    }

    @Test
    public void testDraw(){
        AcesUpDeck ad = new AcesUpDeck();
        SpanishDeck sd = new SpanishDeck();

        // assert that these cards are in the deck
        assertNotNull(ad.draw(new Card(4, Suit.Hearts)));
        assertNotNull(sd.draw(new Card(4, Suit.Coins)));

        // assert that cards are still in the deck
        assertNotNull(ad.draw());
        assertNotNull(sd.draw());

        // draw all the cards
        for (int i = 0; i < 51; i++)
            ad.draw();

        // deck is now empty. draw should return null
        assertNull(ad.draw());
        assertNull(ad.draw(new Card(4, Suit.Hearts)));
    }
}
