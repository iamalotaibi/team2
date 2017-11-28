package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class testBoard {

    @Test
    public void testCustomDeal(){
        Board b = new Board();
        ArrayList<Card> list = new ArrayList<Card>();
        list.add(new Card(0, Suit.Hearts));
        list.add(new Card(3, Suit.Hearts));
        list.add(new Card(6, Suit.Hearts));
        list.add(new Card(9, Suit.Hearts));
        b.customDeal(list);
        assertEquals("0 \u2665",b.deck.deck.get(0).toString());
        assertEquals("3 \u2665",b.deck.deck.get(1).toString());
        assertEquals("6 \u2665",b.deck.deck.get(2).toString());
        assertEquals("9 \u2665",b.deck.deck.get(3).toString());
    }

    @Test
    public void testRemoveFunction(){
        Board b = new Board();
        b.deck = new AcesUpDeck();
        b.dealFour();
        b.remove(2);
        assertEquals(0, b.cols.get(2).size());
    }
}
