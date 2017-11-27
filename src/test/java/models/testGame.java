package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class testGame {

    @Test
    public void testGameCreation(){
        Game g1 = new AcesUpGame();
        assertNotNull(g1);

        Game g2 = new SpanishGame();
        assertNotNull(g2);
    }

    @Test
    public void testGameStart(){
        Game g1 = new AcesUpGame();
        g1.deck.shuffle();
        g1.dealFour();
        assertEquals(1, g1.cols.get(0).size());
        assertEquals(1, g1.cols.get(1).size());
        assertEquals(1, g1.cols.get(2).size());
        assertEquals(1, g1.cols.get(3).size());

        Game g2 = new SpanishGame();
        g2.deck.shuffle();
        g2.dealFour();
        assertEquals(1, g2.cols.get(0).size());
        assertEquals(1, g2.cols.get(1).size());
        assertEquals(1, g2.cols.get(2).size());
        assertEquals(1, g2.cols.get(3).size());
    }

    @Test
    public void testRemoveFunction(){
        Game g1 = new AcesUpGame();
        g1.dealFour();

        // test removal of card that can't be removed
        g1.remove(3);
        assertNotEquals(0, g1.cols.get(3).size());

        // test removal of card that can be removed
        g1.remove(2);
        assertEquals(0, g1.cols.get(2).size());


        Game g2 = new SpanishGame();
        g2.dealFour();

        // test removal of card that can't be removed
        g2.remove(3);
        assertNotEquals(0, g2.cols.get(3).size());

        // test removal of card that can be removed
        g2.remove(2);
        assertEquals(0, g2.cols.get(2).size());
    }
}
