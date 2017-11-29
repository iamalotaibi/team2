package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import static models.Suit.*;

public class Card implements Serializable {
    public final int value;
    public final Suit suit;
    public String htmlString = new String("");

    @JsonCreator
    public Card(@JsonProperty("value") int value, @JsonProperty("suit") Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        String str = "";
        switch (value) {
            case 11:
                str = "J ";
                break;
            case 12:
                str = "Q ";
                break;
            case 13:
                str = "K ";
                break;
            case 14:
                str = "A ";
                break;
            default:
                str = value + " ";
                break;
        }
        switch (suit) {
            case Hearts:
                str = str + "\u2665";
                break;
            case Spades:
                str = str + "\u2660";
                break;
            case Diamonds:
                str = str + "\u2666";
                break;
            case Clubs:
                str = str + "\u2663";
                break;
        }
        return str;
    }

    public String htmlString(Card card) {
        String str = "";
        switch(card.suit) {
        case Hearts:
            str += "<span class=\"redCard\">\u2665</span>";
            break;
        case Spades:
            str += "<span class=\"blackCard\">\u2660</span>";
            break;
        case Diamonds:
            str += "<span class=\"redCard\">\u2666</span>";
            break;
        case Clubs:
            str += "<span class=\"blackCard\">\u2663</span>";
            break;
    }

    str += "<sub class=\"cardID\">";
    switch(card.value) {
        case 11:
            str += "J";
            break;
        case 12:
            str += "Q";
            break;
        case 13:
            str += "K";
            break;
        case 14:
            str += "A";
            break;
        default:
            str += card.value;
            break;
    }
    str += "</sub>";

    return str;

    }

//    function processCard(card) {
//    var str = "";
//
//    switch(card.suit) {
//        case "Hearts":
//            str += "<span class=\"redCard\">\u2665</span>";
//            break;
//        case "Spades":
//            str += "<span class=\"blackCard\">\u2660</span>";
//            break;
//        case "Diamonds":
//            str += "<span class=\"redCard\">\u2666</span>";
//            break;
//        case "Clubs":
//            str += "<span class=\"blackCard\">\u2663</span>";
//            break;
//    }
//
//    str += "<sub class=\"cardID\">";
//    switch(card.value) {
//        case 11:
//            str += "J";
//            break;
//        case 12:
//            str += "Q";
//            break;
//        case 13:
//            str += "K";
//            break;
//        case 14:
//            str += "A";
//            break;
//        default:
//            str += card.value;
//            break;
//    }
//    str += "</sub>";
//
//    return str;
//}
}
