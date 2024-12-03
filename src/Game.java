import java.util.ArrayList;
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

    public boolean checkHand(Player player, String choice){
        if(player.getHand().contains(choice)){
            return true;
        }
        return false;
    }

    // 1. Ask player for what rank card they want to fish for
    // 2. Check to make sure player contains at least card of the rank that they are asking for
    // 3. if opponent has a card of that rank then it adds that card to player hand and removes it from player 2
    // 4. If player has 4 of kind then gives player a point and the 4 get removed from opponent
    public void playRound(Player player, Player opponent){
        Scanner input = new Scanner(System.in);
        System.out.println(player.getName() + "'s turn: what card would you like to fish for?");
        String choice = input.nextLine();

        if(!checkHand(player, choice)){
            System.out.println("Invalid Input");
            return;
        }

        // If players choice matches opponents card add it to new arraylist
        ArrayList<Card> matching = new ArrayList<Card>();
        for(Card card : opponent.getHand()){
            if(card.getRank().equals(choice)){
                matching.add(card);
            }
        }

        // If matching arraylist isnt empty then transfer all of opponents cards to player
        if(!matching.isEmpty()){
            for(Card card: matching){
                opponent.getHand().remove(card);
                player.addCard(card);
            }
            System.out.println(opponent.getName() + " gave you " + matching.size() + " cards");
        }
        // If there were no matches then player takes card from empty pile
        else
        {

        }


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
