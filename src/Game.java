import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;

    public Game(){
        // Make a new deck for game
        String[] rank = {"Salmon","Tuna","Cod","Catfish","Bass","Trout","Herring","Mackerel", "Sardine","Haddock"};
        String[] suits = {"Red", "Blue", "Green","Pink"};
        int[] values = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        this.deck = new Deck(rank, suits, values);

        // Get and set players name
        Scanner input = new Scanner(System.in);
        System.out.println("Player name: ");
        String name = input.nextLine();

//        for (int i = 0; i < 6; i++){
//
//        }
        this.player = new Player(name);
    }
    public static void printInstructions(){
        return "instructions: ";
    }

    public void playGame(){
        deck.shuffle();
        printInstructions();

    }
    public static void main(String[] args) {

    }
}
