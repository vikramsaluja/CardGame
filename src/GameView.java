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






    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);

        g.setColor(Color.red);
        String instructions = "Instructions: \nThis is a 2 player game where each player will get get random hand" +
                "containing a total of 6 cards.\nThe objective of the game is to get as many sets of matching cards or" +
                " 'books'. When it is your turn you will ask the opposing player\nfor a card of certain rank and if " +
                "the other player has a card you will recieve it and get to ask again.\nHowever if you ask for a rank " +
                " that they do not have, you will randomly be dealt a card from the remaining pile of cards.\nThe game" +
                " ends when there are not cards left in the pile and whoever has the most 'books' is the winner!\n" +
                "NOTE: when user inputs are case and spelling sensative\n";
        g.drawString("Instructions:", 30, 200);
        g.drawString("This is a 2 player game where each player will get get random hand containing a total of 6" +
                " cards. The objective of the game is to get as", 30,220);
        g.drawString("many sets of matching cards or 'books'. When it is your turn you will ask the opposing" +
                " for a card of a certain rank and if the other player", 30, 240);
        g.drawString("has that card you will recive it and get to ask again. However, if you ask for a rank" +
                " that they do not have, you will randomly be dealt", 30, 260);
        g.drawString("a card from the remaing pile of cards. The game ends when there are no cards left in" +
                " the pile and whoever has the most 'books' wins!", 30, 280);
        g.drawString("NOTE: the user inputs are case and spelling sensative", 290, 305);
    }
}
