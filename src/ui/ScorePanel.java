package ui;

import model.BFGame;

import javax.swing.*;
import java.awt.*;

// Represents the panel rendering the score and extra lives
public class ScorePanel extends JPanel {

    private static final String SCORE_TXT = "Current score: ";
    private static final String LIVES_TXT = "Extra lives: ";
    private static final int LB_WIDTH = 200;
    private static final int LB_HEIGHT = 40;

    private BFGame game;
    private JLabel scoreLb;
    private JLabel livesLb;
    private JLabel options;

    // EFFECTS: sets background color and constructs label components
    // and updates the score from the game
    public ScorePanel(BFGame g) {
        this.game = g;
        setBackground(new Color(219, 134, 165));
        setLayout(new BorderLayout());

        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(new Color(219, 134, 165));

        scoreLb = new JLabel(SCORE_TXT + game.getFullness(), SwingConstants.CENTER);
        scoreLb.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));
        livesLb = new JLabel(LIVES_TXT + game.getLives(), SwingConstants.CENTER);
        livesLb.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));
        statsPanel.add(scoreLb);
        statsPanel.add(livesLb);

        options = new JLabel("Press ⓧ to save game. \nPress ⓟ to reload previous Bunny. \nPress ⓠ to quit game.",
                SwingConstants.CENTER);
        options.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));

        add(statsPanel, BorderLayout.NORTH);
        add(Box.createVerticalStrut(10));
        add(options, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS:  updates Bunny's fullness and extra lives left in game
    public void update() {
        scoreLb.setText(SCORE_TXT + game.getFullness());
        livesLb.setText(LIVES_TXT + game.getLives());
        repaint();
    }
}