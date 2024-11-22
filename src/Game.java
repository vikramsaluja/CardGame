import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;

    public Game(){
        // Make a new deck for game
        String[] rank = {"Salmon","Tuna","Cod","Catfish","Bass","Trout","Herring","Mackerel", "Sardine","Haddock"};
        String[] suits = {"Red", "Blue", "Green","Pink"};
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
        for (int i = 0; i < 6; i++){
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
    }
    public static void printInstructions(){
        System.out.println("instructions: \n");

    }

    public void playGame(){
        printInstructions();
        // Print out both the players starting hand
        System.out.println(player1.getName() + "'s cards:" + player1.getHand());
        System.out.println(player2.getName() + "'s cards:" + player2.getHand());


    }
    public static void main(String[] args) {
        Game goFish = new Game();
        goFish.playGame();
    }

//    Things left to do:
//    1. make it so cards that are dealt in the starting hand are not left in the pile
//    2. players alternate asking each other for cards and adds it to there hand to make piles until there are no remain cards

}
