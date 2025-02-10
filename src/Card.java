import javax.swing.*;
import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private Image image;

    // Constructor
    public Card(String rank, String suit, int value, String marker) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        String fileName = "Resources/" + marker;
        this.image = new ImageIcon(fileName).getImage();
    }

    // Getters and setter for instance variables
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public Image getImage(){
        return this.image;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    // Takes in 2 cards and returns if they have the same suit
    public static boolean sameSuit(Card card, Card newCard){
        if (card.getSuit().equals(newCard.getSuit())) {
            return true;
        }
        else {
            return false;
        }
    }


    public String toString() {
        return suit + " " +  rank;

    }
}
