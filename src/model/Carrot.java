package model;

import java.awt.*;

// Represents a carrot that Bunny can eat
public class Carrot extends GameItem {

    public static final int FULLNESS_PER_CARROT = 10;
    private static final int width = 73;
    private static final int height = 28;

    private Bunny bunny;

    // EFFECTS: constructs a carrot at (x, y)
    public Carrot(int x, int y) {
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