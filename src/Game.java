import javax.swing.*;

public class Game {

    private JFrame frame;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setSize(525, 550);
        frame.setTitle("Tic-Tac-Toe");

        frame.setVisible(true);
    }

    public void playGame() {
        frame.add(new BoardPanel());
    }

    public static void main(String[] args) {
        System.out.println("Welcome to tic tac toe!");
        Game game = new Game();
        game.playGame();
    }

}
