package model;

public abstract class GameItem {

    private static final int JIGGLE_X = 1;
    public static final int DY = 2;

    protected int posX;
    protected int posY;

    // EFFECTS: constructs a game item at (x, y)
    public GameItem(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    // getters
    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    // MODIFIES: this
    // EFFECTS: moves the item down by DY units and randomly moves in  x-direction
    // no more than JIGGLE_X
    public void move() {
        posX = posX + BFGame.RND.nextInt(2 * JIGGLE_X + 1) - JIGGLE_X;
        posY = posY + DY;

        handleBoundary();
    }

    // MODIFIES: bunny
    // EFFECTS: adds fullness to Bunny
    public void addFullness(Bunny bunny) {
        bunny.addFullness();
    }

    // MODIFIES: bunny
    // EFFECTS: deducts a life from Bunny
    public void removeLife(Bunny bunny) {
        bunny.removeLife();
    }

    // MODIFIES: bunny
    // EFFECTS: adds a life to Bunny
    public void addLife(Bunny bunny) {
        bunny.addLife();
    }

    // MODIFIES: this
    // EFFECTS: item is restricted to window's x-boundaries
    public void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX > BFGame.WIDTH - 85) {
            posX = BFGame.WIDTH - 85;
        }
    }

    // EFFECTS: return true if item caught by Bunny, false otherwise
    public abstract boolean caught(Bunny b);
}