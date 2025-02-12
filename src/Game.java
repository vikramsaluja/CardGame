// Vikram Saluja Go Fish Card Game

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private int state;

    private GameView window;

    public Game() {
        // Set the state to 0
        this.state = 0;
        // Create Window
        this.window = new GameView(this);
        window.repaint();
        // Make a new deck for game
        String[] rank = {"Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "Six"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        this.deck = new Deck(rank, suits, values);

        // shuffle deck every time a new game is created
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

    public int getState() {
        return state;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    // Prints instructions of game
    public static void printInstructions() {
        System.out.println("instructions: \nThis is a 2 player game where each player will get get random hand" +
                "containing a total of 6 cards.\nThe objective of the game is to get as many sets of matching cards or" +
                " 'books'. When it is your turn you will ask the opposing player\nfor a card of certain rank and if " +
                "the other player has a card you will recieve it and get to ask again.\nHowever if you ask for a rank " +
                " that they do not have, you will randomly be dealt a card from the remaining pile of cards.\nThe game" +
                " ends when there are not cards left in the pile and whoever has the most 'books' is the winner!\n" +
                "NOTE: when user inputs are case and spelling sensative\n");

    }



    // Checks which player has more points and returns
    public String gameOver() {
        // Set the state to 2 to change screen
        this.state = 2;
        window.repaint();

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

        boolean swap = false;
        // If players choice matches opponents card add it to new arraylist
        ArrayList<Card> matching = new ArrayList<Card>();
        for(int i = 0; i < opponent.getHand().size(); i++){
            if(opponent.getHand().get(i).getSuit().equals(choice)){
                matching.add(opponent.getHand().get(i));
                // Add opponents matching cards to player's hand
                player.addCard(opponent.getHand().get(i));
                // Remove the matching cards from opponents hands
                opponent.getHand().remove(i);
                i--;
                // Indicate that there was a matching card
                swap = true;
            }
        }
        if(swap) {
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
            // Tell the user that there are no cards left in the pile
            else {
                System.out.println("No cards left in pile!");
            }
        }

        System.out.println(checkBooks(player));
    }

    public String checkBooks(Player player){
        ArrayList<Card> playerHand = player.getHand();
        int newBooks = 0;

        // For each card in opponent's hand
        int i = 0;
        while(i < playerHand.size()){
            // Make a copy of players hand
            ArrayList<Card> copyHand = new ArrayList<Card>();
//            copyHand = playerHand;
            for(int h = 0; h < playerHand.size(); h++){
                copyHand.add(playerHand.get(h));
            }
            int counter = 0;

            // Set the card that is being checked
            Card check = playerHand.get(i);

            // Find all matches to card and remove it from copy hand
            for(int j = 0; j < playerHand.size(); j++){
                if(Card.sameSuit(check, playerHand.get(j))){
                    counter++;
                }
            }

            // If the player does have 4 kind then set the players hand to the copy and increment points
            if (counter == 4) {
                newBooks += 1;
                player.addPoints(1);
            }
            else {
                // If there is no match increment because array list has not shifted down
                i++;
            }
        }
        // After each round is played tell user how many books they have
        return player.getName() + " has " + newBooks + " new Books!";
    }

    // Method that runs the game
    public void playGame() {
        printInstructions();

        Scanner input = new Scanner(System.in);
        System.out.println("Click Enter to Play!");
        input.nextLine();

        // Set the state of the game to play
        this.state = 1;
        window.repaint();


        int turn = 0;
        // Keep playing rounds when cards are left in the pile
        while (!deck.isEmpty()) {
            // Print out both the players hands
            System.out.println(player1.getName() + "'s cards:" + player1.getHand());
            System.out.println(player2.getName() + "'s cards:" + player2.getHand());

            window.repaint();

            // Alternate which players turn it is
            if (turn % 2 == 0) {
                playRound(player1, player2);
                turn++;
            }
            else {
                playRound(player2, player1);
                turn++;
            }
        }

        // When deck is empty end game and print out winner
        gameOver();
    }

    // Main method
    public static void main(String[] args) {
        Game goFish = new Game();
        // Run play game
        goFish.playGame();
    }


}
