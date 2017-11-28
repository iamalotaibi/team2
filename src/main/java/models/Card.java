package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Card implements Serializable {
    public final int value;
    public final Suit suit;

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
                str += value;
                break;
        }
        str += " ";
        switch (suit) {
            case Hearts:
                str += "\u2665";
                break;
            case Spades:
                str += "\u2660";
                break;
            case Diamonds:
                str += "\u2666";
                break;
            case Clubs:
                str += "\u2663";
                break;
            case Coins:
                str += "Coins";
                break;
            case Cups:
                str += "Cups";
                break;
            case Swords:
                str += "Swords";
                break;
            case Jokers:
                str += "Jokers";
                break;
        }
        return str;
    }
}
