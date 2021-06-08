import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class BoardPanel extends JPanel implements MouseListener{

    private Graphics2D g2D;

    private Point[] boardPoints = new Point[9];
    private boolean[] Os = new boolean[9];
    private boolean[] Xs = new boolean[9];

    private JLabel results;
    private boolean turn = true;
    private int numTurns = 0;
    private boolean endGame = false;

    private int[][] winStates = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {2, 4, 6}
    };
    
    public BoardPanel() {
        this.setSize(500, 500);
        this.setLayout(null);
        this.addMouseListener(this);

        boardPoints[0] = new Point(100, 100);
        boardPoints[1] = new Point(200, 100);
        boardPoints[2] = new Point(300, 100);
        boardPoints[3] = new Point(100, 200);
        boardPoints[4] = new Point(200, 200);
        boardPoints[5] = new Point(300, 200);
        boardPoints[6] = new Point(100, 300);
        boardPoints[7] = new Point(200, 300);
        boardPoints[8] = new Point(300, 300);

        results = new JLabel();
        this.add(results);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(4));
        g2D.draw(new Line2D.Double(200, 100, 200, 400));
        g2D.draw(new Line2D.Double(300, 100, 300, 400));
        g2D.draw(new Line2D.Double(100, 200, 400, 200));
        g2D.draw(new Line2D.Double(100, 300, 400, 300));

        for (int i = 0; i < boardPoints.length; i++) {
            if (Os[i]) {
                g2D.setColor(Color.RED);
                g2D.draw(new Ellipse2D.Double(boardPoints[i].x + 15, boardPoints[i].y + 15, 70, 70));
            }
            if (Xs[i]) {
                g2D.setColor(Color.BLUE);
                g2D.draw(new Line2D.Double(boardPoints[i].x + 15, boardPoints[i].y + 15, boardPoints[i].x + 85, boardPoints[i].y + 85));
                g2D.draw(new Line2D.Double(boardPoints[i].x + 15, boardPoints[i].y + 85, boardPoints[i].x + 85, boardPoints[i].y + 15));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!endGame) {

            Point clickPoint = new Point();
            clickPoint.x = e.getX();
            clickPoint.y = e.getY();

            for (int i = 0; i < boardPoints.length; i++) {
                if (clickPoint.x >= boardPoints[i].x && clickPoint.y >= boardPoints[i].y && clickPoint.x <= boardPoints[i].x + 100 && clickPoint.y <= boardPoints[i].y + 100) {
                    if (turn) {
                        if (!Xs[i]) {
                            Os[i] = true;
                            for (int[] state : winStates) {
                                if (Os[state[0]] && Os[state[1]] && Os[state[2]]) {
                                    results.setLocation(190, 35);
                                    results.setSize(125, 30);
                                    results.setFont(results.getFont().deriveFont(30.0f));
                                    results.setText("O wins!!");
                                    endGame = true;
                                    break;
                                }
                            }
                            turn = !turn;
                        }
                    } else {
                        if (!Os[i]) {
                            Xs[i] = true;
                            for (int[] state : winStates) {
                                if (Xs[state[0]] && Xs[state[1]] && Xs[state[2]]) {
                                    results.setLocation(190, 35);
                                    results.setSize(125, 30);
                                    results.setFont(results.getFont().deriveFont(30.0f));
                                    results.setText("X wins!!");
                                    endGame = true;
                                    break;
                                }
                            }
                            turn = !turn;
                        }
                    }
                }
            }
            numTurns++;
            if (numTurns >= 9 && !endGame) {
                results.setLocation(160, 35);
                results.setSize(175, 30);
                results.setFont(results.getFont().deriveFont(30.0f));
                results.setText("Cat Game :(");
                endGame = true;
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    
}
