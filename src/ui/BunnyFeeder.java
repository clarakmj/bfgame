package ui;

import model.BFGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Represents the main window where Bunny Feeder is played
// CREDITS: SpaceInvaders game from CPSC-210
public class BunnyFeeder extends JFrame {

    private static final int INTERVAL = 20;

    private BFGame game;
    private GamePanel gp;
    private ScorePanel sp;
    private NotePanel np;

    // EFFECTS: construct the main window where Bunny Feeder is played
    public BunnyFeeder() {
        super("Bunny Feeder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new BFGame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        np = new NotePanel(game);
        add(gp);
        add(np, BorderLayout.NORTH);
        add(sp, BorderLayout.SOUTH);
        addKeyListener(new KeyHandler());
        pack();
        onCenter();
        setVisible(true);
        addTimer();
    }

    // EFFECTS: respond to user key inputs
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

    // EFFECTS: initialize a timer to update the game at every INTERVAL
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gp.repaint();
                sp.update();
            }
        });

        t.start();
    }

    // MODIFIES: this
    // EFFECTS: set the game window on center of screen
    private void onCenter() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // starts playing game
    public static void main(String[] args) {
        new BunnyFeeder();
    }
}