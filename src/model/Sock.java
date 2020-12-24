package model;

import java.awt.*;

// Represents a sock that Bunny can eat
public class Sock extends GameItem {

    private Bunny bunny;
    private static final int width = 45;
    private static final int height = 43;

    // EFFECTS: constructs a sock at (x, y)
    public Sock(int x, int y) {
        super(x, y);
    }

    // EFFECTS: return true if Bunny caught carrot, false otherwise
    public boolean caught(Bunny b) {
        int bunnyWidth = b.getWidth();
        int bunnyHeight = b.getHeight();
        int bunnyX = b.getPosX();
        int bunnyY = b.Y_POS;

        Rectangle itemBoundingRect = new Rectangle(getX(), getY(), width, height);
        Rectangle bunnyBoundingRect = new Rectangle(bunnyX, bunnyY, bunnyWidth, bunnyHeight);
        return itemBoundingRect.intersects(bunnyBoundingRect);
    }

}