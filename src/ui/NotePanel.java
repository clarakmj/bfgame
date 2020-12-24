package ui;

import model.BFGame;

import javax.swing.*;
import java.awt.*;

public class NotePanel extends JPanel {

    private static final int LB_WIDTH = 200;
    private static final int LB_HEIGHT = 30;

    private BFGame game;
    private JLabel warning;
    private JLabel note;

    public NotePanel(BFGame g) {
        this.game = g;
        setBackground(new Color(214, 233, 245));
        setLayout(new BorderLayout());

        JPanel notePanel = new JPanel();
        notePanel.setBackground(new Color(214, 233, 245));

        warning = new JLabel("WARNING: one of these carrots can be poisonous & deducts Bunny's life!",
                SwingConstants.CENTER);
        warning.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));
        note = new JLabel("But there may be a magical sock that awards one life back!", SwingConstants.CENTER);
        note.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));

        add(warning, BorderLayout.NORTH);
        add(Box.createVerticalStrut(10));
        add(note, BorderLayout.SOUTH);
    }
}