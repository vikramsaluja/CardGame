import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;

    public Game() {
        // Make a new deck for game
        String[] rank = {"Salmon", "Tuna", "Cod", "Catfish", "Bass", "Trout", "Herring", "Mackerel", "Sardine", "Haddock"};
        String[] suits = {"Red", "Blue", "Green", "Pink"};
        int[] values = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        this.deck = new Deck(rank, suits, values);

        deck.shuffle();

        Scanner input = new Scanner(System.in);

        // Get and set players  1 name
        System.out.println("Player 1 name: ");
        String name = input.nextLine();
        this.player1 = new Player(name);

        // Get and set players  2 name
        System.out.println("Player 2 name: ");
        name = input.nextLine();
        this.player2 = new Player(name);

        // Deals 6 cards to player for there starting hand
        for (int i = 0; i < 6; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
    }

    public static void printInstructions() {
        System.out.println("instructions: \n");

    }


    // Needs to be debugged
//    public boolean checkHand(Player player, String choice) {
//        if (player.getHand().contains(choice)) {
//            return true;
//        }
//        return false;
//    }

    // Checks which player has more points and returns
    public String gameOver() {
        if (player1.getPoints() > player2.getPoints()) {
            return "Game over! \n" + player1.getName() + " is the winner with " + player1.getPoints() + " books!";
        } else {
            return "Game over! \n" + player2.getName() + " is the winner with " + player2.getPoints() + " books!";
        }
    }

    // 1. Ask player for what rank card they want to fish for
    // 2. Check to make sure player contains at least card of the rank that they are asking for
    // 3. if opponent has a card of that rank then it adds that card to player hand and removes it from player 2
    // 4. If player has 4 of kind then gives player a point and the 4 get removed from opponent
    public void playRound(Player player, Player opponent) {
        Scanner input = new Scanner(System.in);
        System.out.println(player.getName() + "'s turn: what card would you like to fish for?");
        String choice = input.nextLine();

//        if (!checkHand(player, choice)) {
//            System.out.println("Invalid Input");
//            return;
//        }

        // If players choice matches opponents card add it to new arraylist
        ArrayList<Card> matching = new ArrayList<Card>();
        for (Card card : opponent.getHand()) {
            if (card.getRank().equals(choice)) {
                matching.add(card);
            }
        }

        // If matching arraylist isnt empty then transfer all of opponents cards to player
        if (!matching.isEmpty()) {
            for (Card card : matching) {
                opponent.getHand().remove(card);
                player.addCard(card);
            }
            System.out.println(opponent.getName() + " gave you " + matching.size() + " cards");
        }
        // If there were no matches then player takes card from empty pile
        else {
            System.out.println("No Match, Pick card from a pile");

            Card newCard = deck.deal();
            if (newCard != null) {
                player.addCard(newCard);
                System.out.println(player.getName() + " pulled a " + newCard);
            }
            // If there are no cards left in the deck, end game
            else {
                System.out.println("No cards left in pile!");
                System.out.println(gameOver());
            }
        }
        System.out.println(checkBooks(player, player.getHand()));
    }

    public String checkBooks(Player player, ArrayList<Card> playerHand){
        int newBooks = 0;
        for(int i =  4; i > 0; i--){
            ArrayList<Card> copyHand = new ArrayList<Card>();
            int counter = 0;
            Card check = playerHand.get(i);

            for(int j = playerHand.size(); j < 1; j--){
                if(Card.sameSuit(check, playerHand.get(j))){
                    counter++;
                    playerHand.remove(j);
                }
            }

            // If the player does not have 4 of kind then reset the players hand
            if (counter != 4){
                player.setHand(copyHand);
                playerHand = copyHand;
            }
            else {
                newBooks += 1;
                player.addPoints(1);
            }
        }
        return player.getName() + " has " + newBooks + " new Books!";
    }


    public void playGame() {
        printInstructions();
        // Print out both the players starting hand
        System.out.println(player1.getName() + "'s cards:" + player1.getHand());
        System.out.println(player2.getName() + "'s cards:" + player2.getHand());

        int turn = 0;
        while (!deck.isEmpty()) {
            if (turn % 2 == 0) {
                playRound(player1, player2);
                turn++;
            }
            else {
                playRound(player2, player1);
                turn++;
            }
        }
    }

    public static void main(String[] args) {
        Game goFish = new Game();
        goFish.playGame();
    }


}
