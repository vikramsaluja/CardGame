import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    // Constructor takes in player name and sets points to 0
    public Player(String name){
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<>();
    }

    // Constructor takes in player name, hand of cards, sets points to 0
    public Player(String name, ArrayList<Card> newHand){
        this.name = name;
        this.hand = newHand;
        this.points = points;
    }

    // Getter and setter methods for instance variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> cards) {
        this.hand = cards;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Takes in number of points and adds it to player's points
    public void addPoints(int points){
        this.points += points;
    }

    // Adds new card to the players hand
    public void addCard(Card newCard){
        hand.add(newCard);
    }



    // To string method
    @Override
    public String toString() {
        return (this.name + " has " + this.points + " points\n" + this.name + "'s cards " + this.hand);
    }
}
