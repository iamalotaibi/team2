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
        list.add(new Card(0, Suit.Hearts, Card.getHtmlString(0,Suit.Hearts)));
        list.add(new Card(3, Suit.Hearts, Card.getHtmlString(3,Suit.Hearts)));
        list.add(new Card(6, Suit.Hearts, Card.getHtmlString(6,Suit.Hearts)));
        list.add(new Card(9, Suit.Hearts, Card.getHtmlString(9,Suit.Hearts)));
        b.customDeal(list);
        assertEquals("0 \u2665",list.get(0).toString());
        assertEquals("3 \u2665",list.get(1).toString());
        assertEquals("6 \u2665",list.get(2).toString());
        assertEquals("9 \u2665",list.get(3).toString());
    }

    @Test
    public void testRemoveFunction(){
        Board b = new Board();
        b.deck = new AcesUpDeck();
        b.dealFour();
        b.remove(2);
        assertEquals(0, b.cols.get(2).size());
        b.remove(2);
    }

    @Test
    public void testMoveFunction(){
        Board b = new Board();
        ArrayList<Card> list = new ArrayList<Card>();
        list.add(new Card(0, Suit.Hearts, Card.getHtmlString(0,Suit.Hearts)));
        list.add(new Card(3, Suit.Hearts, Card.getHtmlString(3,Suit.Hearts)));
        list.add(new Card(6, Suit.Hearts, Card.getHtmlString(6,Suit.Hearts)));
        list.add(new Card(14, Suit.Hearts, Card.getHtmlString(9,Suit.Hearts)));
        b.customDeal(list);
        b.remove(0);
        b.move(3,2);
        b.move(0,1);
    }
}
