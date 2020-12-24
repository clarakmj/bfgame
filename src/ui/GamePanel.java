package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// Represents the panel in which the game is displayed
public class GamePanel extends JPanel {

    private static final String OVER = "Game over!";
    private static final String TY = "Thank you for playing Bunny Feeder ❤";
    private static final String REPLAY = "Press ⓡ to replay";

    private BFGame game;
    BufferedImage imgBunny = null;
    BufferedImage imgCarrot = null;
    BufferedImage imgSock = null;

    // EFFECTS: set size and background color of game panel
    // and updates with the game to be rendered
    public GamePanel(BFGame g) {
        setPreferredSize(new Dimension(BFGame.WIDTH, BFGame.HEIGHT));
        setBackground(Color.PINK);
        this.game = g;
    }

    // EFFECTS: render game components while playing
    // and render game over components if over
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.isOver()) {
            gameOver(g);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws components onto g
    private void drawGame(Graphics g) {
        drawBunny(g);
        drawCarrots(g);
        drawSocks(g);
    }

    // MODIFIES: g
    // EFFECTS: draws Bunny onto g
    private void drawBunny(Graphics g) {
        Bunny b = game.getBunny();
        try {
            imgBunny = ImageIO.read(getClass().getResource("bunny.png"));
            g.drawImage(imgBunny, b.getPosX(), b.Y_POS, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: g
    // EFFECTS: draws carrots onto g
    private void drawCarrots(Graphics g) {
        for (Carrot next : game.getCarrots()) {
            drawCarrot(g, next);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws carrot c onto g
    private void drawCarrot(Graphics g, Carrot c) {
        try {
            imgCarrot = ImageIO.read(getClass().getResource("carrot.png"));
            g.drawImage(imgCarrot, c.getX(), c.getY(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: g
    // EFFECTS: draws socks onto g
    private void drawSocks(Graphics g) {
        for (Sock next : game.getSocks()) {
            drawSock(g, next);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws sock s onto g
    private void drawSock(Graphics g, Sock s) {
        try {
            imgSock = ImageIO.read(getClass().getResource("sock.png"));
            g.drawImage(imgSock, s.getX(), s.getY(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: g
    // EFFECTS: draws OVER, TY, and REPLAY messages onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(67, 70, 70));
        g.setFont(new Font("Serif", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, BFGame.HEIGHT / 2);
        centreString(TY, g, fm, BFGame.HEIGHT / 2 + 25);
        centreString(REPLAY, g, fm, BFGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // EFFECTS: places String at center of window with given posY
    private void centreString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (BFGame.WIDTH - width) / 2, posY);
    }
}