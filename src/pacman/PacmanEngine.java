package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PacmanEngine extends JPanel implements ActionListener, KeyListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MusicThread mus1 = new MusicThread();
    private Maze maze = new Maze();
    private int[][] coordinateBrick = new int[106][2];
    private ArrayList<int[]> coordinateStar = new ArrayList<>();
    private int[] currentPosition = new int[2];
    private int x = 0, y = 0, velx = 0, vely = 0;
    private ImageIcon imgRight = new ImageIcon("images/pacman_right.gif");
    private ImageIcon imgLeft = new ImageIcon("images/pacman_left.gif");
    private ImageIcon imgUp = new ImageIcon("images/pacman_up.gif");
    private ImageIcon imgDown = new ImageIcon("images/pacman_down.gif");
    private ImageIcon imgMaze = new ImageIcon("images/brick.jpg");
    private ImageIcon imgStart = new ImageIcon("images/pacmanStart.gif");
    private ImageIcon imgStar = new ImageIcon("images/star.gif");
    private ImageIcon menu = new ImageIcon("images/menu.jpg");

    private Timer t = new Timer(2, this);
    private Timer timer;

    public PacmanEngine() {
        mus1.PlayStart();
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        coordinateBrick = maze.getCoordinateBrick();
        coordinateStar = maze.getCoordinateStar();
    }

    private boolean startPoint = true;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLUE);

        g.drawImage(menu.getImage(), 0, 750, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 45));

        if (coordinateStar.size() == 0) {
            g.drawString("WIN!!!", 300, 790);
        } else {
            g.drawString("SCORE: " + score, 200, 790);
        }

        for (int i = 0; i < coordinateBrick.length; i++) {
            g.drawImage(imgMaze.getImage(), coordinateBrick[i][0], coordinateBrick[i][1], null);
        }

        for (int i = 0; i < coordinateStar.size(); i++) {
            g.drawImage(imgStar.getImage(), coordinateStar.get(i)[0], coordinateStar.get(i)[1], null);
        }

        if (startPoint) {
            g.drawImage(imgStart.getImage(), x, y, null);
            startPoint = (x == 0 && y == 0) ? true : false;
        } else if (coordinateStar.size() == 0) {
            g.drawImage(imgStart.getImage(), x, y, null);
        } else if (code == KeyEvent.VK_DOWN) {
            g.drawImage(imgDown.getImage(), x, y, null);
        } else if (code == KeyEvent.VK_UP) {
            g.drawImage(imgUp.getImage(), x, y, null);
        } else if (code == KeyEvent.VK_RIGHT) {
            g.drawImage(imgRight.getImage(), x, y, null);
        } else if (code == KeyEvent.VK_LEFT) {
            g.drawImage(imgLeft.getImage(), x, y, null);
        }
    }

    private boolean startTimer = false;
    private int score = 0;

    // created special in order to avoid duplicating code
    public void actionRepeated() {
        if (code == KeyEvent.VK_DOWN) {
            down();
        } else if (code == KeyEvent.VK_UP) {
            up();
        } else if (code == KeyEvent.VK_LEFT) {
            left();
        } else if (code == KeyEvent.VK_RIGHT) {
            right();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if ((temp == KeyEvent.VK_LEFT && code == KeyEvent.VK_RIGHT)
                || (code == KeyEvent.VK_LEFT && temp == KeyEvent.VK_RIGHT)
                || (temp == KeyEvent.VK_DOWN && code == KeyEvent.VK_UP)
                || (temp == KeyEvent.VK_UP && code == KeyEvent.VK_DOWN)) {
            code = temp;
            actionRepeated();
        }
        if (x % 50 == 0 && y % 50 == 0) {
            currentPosition[0] = x;
            currentPosition[1] = y;
            code = temp;
            actionRepeated();
        }

        if ((code == KeyEvent.VK_DOWN && y >= 700) || (code == KeyEvent.VK_UP && y <= 0)) {
            vely = 0;
        } else if ((code == KeyEvent.VK_RIGHT && x >= 750) || (code == KeyEvent.VK_LEFT && x <= 0)) {
            velx = 0;
        }

        for (int i = 0; i < coordinateBrick.length; i++) {
            if ((code == KeyEvent.VK_DOWN && currentPosition[0] == coordinateBrick[i][0]
                    && currentPosition[1] + 50 == (coordinateBrick[i][1]))) {
                vely = 0;
                break;
            }
            if ((code == KeyEvent.VK_UP && currentPosition[0] == coordinateBrick[i][0]
                    && currentPosition[1] - 50 == (coordinateBrick[i][1]))) {
                vely = 0;
                break;
            }
            if ((code == KeyEvent.VK_LEFT && currentPosition[0] - 50 == coordinateBrick[i][0]
                    && currentPosition[1] == (coordinateBrick[i][1]))) {
                velx = 0;
                break;
            }
            if ((code == KeyEvent.VK_RIGHT && currentPosition[0] + 50 == coordinateBrick[i][0]
                    && currentPosition[1] == (coordinateBrick[i][1]))) {
                velx = 0;
                break;
            }
        }

        for (int i = 0; i < coordinateStar.size(); i++) {
            if (currentPosition[0] == coordinateStar.get(i)[0] && currentPosition[1] == coordinateStar.get(i)[1]) {
                coordinateStar.remove(i);
                score++;
                mus1.PlaySoundGetStar();
                if (coordinateStar.size() == 0) {
                    timer.stop();
                    mus1.PlayWin();
                }
                break;
            }
        }
        System.out.println("velx =" + velx + ", vely=" + vely + ", x =" + x + ", y=" + y);

        if ((velx != 0 || vely != 0) && !startTimer) {
            startTimer = true;
            timer = new Timer(410, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println(timer.isRunning() + ", x =" + x + ", y=" + y);
                    mus1.PlayEat();
                }
            });
            timer.setRepeats(true);
            timer.start();
        }

        repaint();
        x += velx;
        y += vely;
    }

    public void up() {
        vely = -1;
        velx = 0;
    }

    public void down() {
        vely = 1;
        velx = 0;
    }

    public void left() {
        velx = -1;
        vely = 0;
    }

    public void right() {
        velx = 1;
        vely = 0;
    }

    int code;
    int temp;

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP && y != 0) {
            temp = e.getKeyCode();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y != 700) {
            temp = e.getKeyCode();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && x != 0) {
            temp = e.getKeyCode();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x != 750) {
            temp = e.getKeyCode();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}
