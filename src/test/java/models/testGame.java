package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class testGame {

    @Test
    public void testGameCreation(){
        // Game 1 Tests

        AcesUpGame g1 = new AcesUpGame();
        assertNotNull(g1);


        // Game 2 Tests

        SpanishGame g2 = new SpanishGame();
        assertNotNull(g2);
    }

    @Test
    public void testGameStart(){
        // Game 1 Tests

        AcesUpGame g1 = new AcesUpGame();
        g1.dealFour();
        assertEquals(1, g1.cols.get(0).size());
        assertEquals(1, g1.cols.get(1).size());
        assertEquals(1, g1.cols.get(2).size());
        assertEquals(1, g1.cols.get(3).size());


        // Game 2 Tests

        SpanishGame g2 = new SpanishGame();
        g2.dealFour();
        assertEquals(1, g2.cols.get(0).size());
        assertEquals(1, g2.cols.get(1).size());
        assertEquals(1, g2.cols.get(2).size());
        assertEquals(1, g2.cols.get(3).size());
    }

    @Test
    public void testMoveFunction() {
        AcesUpGame g = new AcesUpGame();
        ArrayList<Card> list = new ArrayList<Card>();
        list.add(new Card(0, Suit.Hearts, Card.getHtmlString(0,Suit.Hearts)));
        list.add(new Card(14, Suit.Spades, Card.getHtmlString(14,Suit.Spades)));
        g.customDeal(list);

        // Invalid move
        g.move(0, 1);
        assertEquals(false, g.isColumnEmpty(0));
        assertEquals(false, g.isColumnEmpty(1));
        assertEquals(true, g.isColumnEmpty(2));
        assertEquals(true, g.isColumnEmpty(3));

        // Invalid move (Only aces can be moved)
        g.move(0, 2);
        assertEquals(false, g.isColumnEmpty(0));
        assertEquals(false, g.isColumnEmpty(1));
        assertEquals(true, g.isColumnEmpty(2));
        assertEquals(true, g.isColumnEmpty(3));

        // Move Ace
        g.move(1, 3);
        assertEquals(false, g.isColumnEmpty(0));
        assertEquals(true, g.isColumnEmpty(1));
        assertEquals(true, g.isColumnEmpty(2));
        assertEquals(false, g.isColumnEmpty(3));
    }

    @Test
    public void testRemoveFunction() {
        // Game 1 Tests

        AcesUpGame g1 = new AcesUpGame();
        ArrayList<Card> list1 = new ArrayList<Card>();
        list1.add(new Card(0, Suit.Hearts, Card.getHtmlString(0,Suit.Hearts)));
        list1.add(new Card(3, Suit.Hearts, Card.getHtmlString(3,Suit.Hearts)));
        list1.add(new Card(6, Suit.Hearts, Card.getHtmlString(6,Suit.Hearts)));
        list1.add(new Card(9, Suit.Hearts, Card.getHtmlString(9,Suit.Hearts)));
        g1.customDeal(list1);

        // test remove at invalid column
        g1.remove(5);
        assertNotEquals(0, g1.cols.get(0).size());
        assertNotEquals(0, g1.cols.get(1).size());
        assertNotEquals(0, g1.cols.get(2).size());
        assertNotEquals(0, g1.cols.get(3).size());

        // test removal of card that can't be removed
        g1.remove(3);
        assertNotEquals(0, g1.cols.get(3).size());


        // test removal of card that can be removed
        g1.remove(2);
        assertEquals(0, g1.cols.get(2).size());


        // Game 2 Tests

        SpanishGame g2 = new SpanishGame();
        ArrayList<Card> list2 = new ArrayList<Card>();
        list2.add(new Card(1, Suit.Clubs, Card.getHtmlString(1,Suit.Clubs)));
        list2.add(new Card(3, Suit.Clubs, Card.getHtmlString(3,Suit.Clubs)));
        list2.add(new Card(6, Suit.Coins, Card.getHtmlString(6,Suit.Coins)));
        list2.add(new Card(9, Suit.Jokers, Card.getHtmlString(9,Suit.Jokers)));
        g2.customDeal(list2);

        // test remove at invalid column
        g2.remove(5);
        assertNotEquals(0, g2.cols.get(0).size());
        assertNotEquals(0, g2.cols.get(1).size());
        assertNotEquals(0, g2.cols.get(2).size());
        assertNotEquals(0, g2.cols.get(3).size());

        // test removal of card that can't be removed
        g2.remove(3);
        assertNotEquals(0, g2.cols.get(0).size());
        assertNotEquals(0, g2.cols.get(1).size());
        assertNotEquals(0, g2.cols.get(2).size());
        assertNotEquals(0, g2.cols.get(3).size());

        // At this point, the columns look like [Clubs, Clubs, Coins, Jokers]
        // test removal of Clubs at column 0
        g2.remove(0);
        assertEquals(0, g2.cols.get(0).size());
        // The other club should not have been removed
        assertNotEquals(0, g2.cols.get(1).size());
        // Coins should not have been touched
        assertNotEquals(0, g2.cols.get(2).size());
        // Jokers should not have been removed either as the other Club should have been used as a match
        assertNotEquals(0, g2.cols.get(3).size());

        // At this point, the columns look like [empty, Clubs, Coins, Jokers]
        // Clubs and Coins should be able to be removed due to the presence of Jokers
        assertEquals(true, g2.isCardRemovable(1));
        assertEquals(true, g2.isCardRemovable(2));
        // The empty column and Jokers should not be able to be removed
        assertEquals(false, g2.isCardRemovable(0));
        assertEquals(false, g2.isCardRemovable(3));
        // Column 0 should be empty and others shouldn't
        assertEquals(true, g2.isColumnEmpty(0));
        assertEquals(false, g2.isColumnEmpty(1));
        assertEquals(false, g2.isColumnEmpty(2));
        assertEquals(false, g2.isColumnEmpty(3));
        // Test removal of Clubs, which should also remove Jokers
        g2.remove(1);
        assertEquals(0, g2.cols.get(0).size());
        assertEquals(0, g2.cols.get(1).size());
        assertEquals(1, g2.cols.get(2).size());
        assertEquals(0, g2.cols.get(3).size());

        // At this point, the columns look like [empty, empty, Coins, empty]
        // test removal of Coins, which can't be removed
        g2.remove(2);
        assertNotEquals(0, g2.cols.get(2).size());
    }

    @Test
    public void testWinLoose() {
        // Game 1 Tests
        AcesUpGame g1 = new AcesUpGame();

        for (int i = 0; i < 13; ++i) {
            g1.dealFour();
        }

        while (g1.getGameEndState() == 0) {
            for (int i = 0; i < 4; ++i)
                g1.remove(i);
        }

        // At this point no card can be moved or removed
        assertEquals(false, g1.isCardRemovable(0));
        assertEquals(false, g1.isCardRemovable(1));
        assertEquals(false, g1.isCardRemovable(2));
        assertEquals(false, g1.isCardRemovable(3));
        assertEquals(false, g1.isCardMovable(0));
        assertEquals(false, g1.isCardMovable(1));
        assertEquals(false, g1.isCardMovable(2));
        assertEquals(false, g1.isCardMovable(3));


        // Game 2 Tests

        SpanishGame g2 = new SpanishGame();

        for (int i = 0; i < 13; ++i) {
            g2.dealFour();
        }

        while (g2.getGameEndState() == 0) {
            for (int i = 0; i < 4; ++i)
                g2.remove(i);
        }

        // At this point no card can be moved or removed
        assertEquals(false, g2.isCardRemovable(0));
        assertEquals(false, g2.isCardRemovable(1));
        assertEquals(false, g2.isCardRemovable(2));
        assertEquals(false, g2.isCardRemovable(3));
        assertEquals(false, g2.isCardMovable(0));
        assertEquals(false, g2.isCardMovable(1));
        assertEquals(false, g2.isCardMovable(2));
        assertEquals(false, g2.isCardMovable(3));
    }
    @Test
    public void testUpdateGameEndState() {
        AcesUpGame g1 = new AcesUpGame();
        assertEquals(0, g1.getScore());
    }
}
