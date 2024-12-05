import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Deck Constructor
    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Card>();
        for (String suit : suits){
            for(int i = 0; i < ranks.length; i++){
                // creates new card and sets variables
                 cards.add(new Card(ranks[i], suit, values[i]));
            }
        }
        cardsLeft = cards.size();
    }

    // Returns true if there are no cards left in deck
    public boolean isEmpty(){
        return cardsLeft == 0;
    }

    // getter method for cards
    public int getCardsLeft(){
        return this.cardsLeft;
    }

    public Card deal(){
        // Returning null if there aren't cards left
        if(isEmpty()){
            return null;
        }
        // increment down every time a card is taken from the deck
        cardsLeft-- ;
        return cards.get(cardsLeft);
    }

    public void shuffle(){
        // For loop that runs starting at the highest index to the lowest
        for(int i = cards.size() - 1; i > 0; i--){
            // Generates a random integer in between 0 and i
            int random = (int) (Math.random() * (i + 1));

            // Swaps card at i a card at a random index
            Card swap = cards.get(i);
            cards.set(i, cards.get(random));
            cards.set(random, swap);

        }
        // Resets the number of cards left in the deck
        cardsLeft = cards.size();
    }

}
