package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class testGame {

    @Test
    public void testGameCreation(){
        Game g = new Game();
        assertNotNull(g);
    }

    @Test
    public void testGameStart(){
        Game g = new Game();
        g.deck.shuffle();
        g.dealFour();
        assertEquals(1, g.cols.get(0).size());
        assertEquals(1, g.cols.get(1).size());
        assertEquals(1, g.cols.get(2).size());
        assertEquals(1, g.cols.get(3).size());
    }

    @Test
    public void testRemoveFunction(){
        Game g = new Game();
        ArrayList<Card> list = new ArrayList<Card>();
        list.add(new Card(0, Suit.Hearts));
        list.add(new Card(3, Suit.Hearts));
        list.add(new Card(6, Suit.Hearts));
        list.add(new Card(9, Suit.Hearts));
        g.customDeal(list);

        // test removal of card that can't be removed
        g.remove(3);
        assertNotEquals(0, g.cols.get(3).size());

        // test removal of card that can be removed
        g.remove(2);
        assertEquals(0, g.cols.get(2).size());
    }
}
