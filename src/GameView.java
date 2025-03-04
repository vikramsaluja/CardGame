import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 700;
    private Game game;

    public GameView(Game game){
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GoFish Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paintInstructions(Graphics g){
        g.setColor(Color.white);

        g.drawString("Go Fish Game", 395, 150);
        g.drawString("Instructions:", 25, 200);
        g.drawString("This is a 2 player game where each player will get get random hand containing a total of 6" +
                " cards. The objective of the game is to get as", 25,220);
        g.drawString("many sets of matching cards or 'books'. When it is your turn you will ask the opposing" +
                " for a card of a certain rank and if the other player", 25, 240);
        g.drawString("has that card you will recive it and get to ask again. However, if you ask for a rank" +
                " that they do not have, you will randomly be dealt", 25, 260);
        g.drawString("a card from the remaing pile of cards. The game ends when there are no cards left in" +
                " the pile and whoever has the most 'books' wins!", 25, 280);
        g.drawString("NOTE: the user inputs are case and spelling sensative", 25, 305);
        g.drawString("Input Player Names", 380, 350);
        g.drawString("Click Enter to Play", 380, 370);
    }

    // Method that paints all the cards for each players hand's
    public void paintHands(Graphics g){
        // Print player 1's hand
        for(int i = 0; i < game.getPlayer1().getHand().size(); i++) {
            g.setColor(Color.white);
            g.drawImage(game.getPlayer1().getHand().get(i).getImage(), 70 + (75 * i), 50, 65, 90, this);
            g.drawString(game.getPlayer1().getName(), 70, 175);
        }
        // Print player 2's hand
        for(int i = 0; i < game.getPlayer2().getHand().size(); i++) {
            g.setColor(Color.white);
            g.drawImage(game.getPlayer2().getHand().get(i).getImage(), 830 - (75 * i), 600, 65, 90, this);
            g.drawString(game.getPlayer2().getName(), 825, 575);
        }
    }

    // Game Over helperMethod which shows the number of books each player had
    public void paintNumBooks(Graphics g){
        g.setColor(Color.white);
        g.drawString(game.getPlayer1().getName() + " Had " + game.getPlayer1().getNumBooks() + " Books", 375, 400);
        g.drawString(game.getPlayer2().getName() + " Had " + game.getPlayer2().getNumBooks() + " Books", 375, 425);
    }


    // Method that paints the game over screen
    public void paintGameOver(Graphics g){
        g.setColor(Color.white);

        // Print out the winner on the screen
        g.drawString(game.gameOver(), 300,350);
        g.drawString("Thank You For Playing!", 365, 300);
        paintNumBooks(g);

        // Paint GoFish icon at bottom of the screen
        g.drawImage(new ImageIcon("Resources/GoFish.png").getImage(),360,495,170,190,this);
    }

    // Paint Method
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);

        // Print the instructions screen
        if (this.game.getState() == 0) {
            paintInstructions(g);
        }
        // Print the main game screen
        else if (this.game.getState() == 1){
            g.drawImage(new ImageIcon("Resources/back.png").getImage(),100, 325,65,90,this);
            paintHands(g);
        }
        // Print the game over screen
        else if(this.game.getState() == 2){
            paintGameOver(g);
        }
    }
}
