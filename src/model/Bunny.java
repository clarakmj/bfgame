package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

import static model.Carrot.FULLNESS_PER_CARROT;

// Represents a bunny having fullness in %, and number of extra lives
// NOTE: bunny is considered dead when has -1 lives
public class Bunny implements Saveable {

    public static final int MAX_FULLNESS = 100;
    public static final int Y_POS = BFGame.HEIGHT - 120;
    public static final int DX = 3;
    private static final int width = 86;
    private static final int height = 113;

    private int posX;
    private int direction;
    private int fullness;
    private int lives;

    // EFFECTS: constructs Bunny at (x, Y_POS) moving right
    public Bunny(int x) {
        this.posX = x;
        direction = 1;
        this.fullness = 0;
        this.lives = 3;
    }

    // EFFECTS: constructs Bunny with given fullness and lives;
    public Bunny(int fullness, int lives) {
        this.fullness = fullness;
        this.lives = lives;
    }

    // MODIFIES: this
    // EFFECTS: moves Bunny in DX units in given direction (right if +, left if -)
    // and restricts Bunny movement within window's x-boundaries
    public void move() {
        posX = posX + direction * DX;

        handleBoundary();
    }

    // getters
    public int getPosX() {
        return posX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFullness() {
        return fullness;
    }

    public int getLives() {
        return lives;
    }

    public void setPosX(int pos) {
        this.posX = pos;
    }

    // REQUIRES: fullness < MAX_FULLNESS
    // MODIFIES: this
    // EFFECTS: adds fullness to Bunny by FULLNESS_PER_CARROT
    public int addFullness() {
        return this.fullness = fullness + FULLNESS_PER_CARROT;
    }

    // REQUIRES: lives >= 0
    // MODIFIES: this
    // EFFECTS: deducts a life from Bunny
    public int removeLife() {
        return this.lives--;
    }

    // REQUIRES: lives >= 0
    // MODIFIES: this
    // EFFECTS: adds a life to Bunny
    public int addLife() {
        return this.lives++;
    }

    // EFFECTS: returns true if Bunny is still alive
    // NOTE: Bunny is alive at 0, dead at -1
    public boolean isAlive() {
        return this.lives >= 0;
    }

    // EFFECTS: return true if Bunny has reached MAX_FULLNESS
    public boolean isFull() {
        return this.fullness == MAX_FULLNESS;
    }

    // MODIFIES: this
    // EFFECTS: faces Bunny in right direction
    public void faceRight() {
        direction = 1;
    }

    // MODIFIES: this
    // EFFECTS: faces Bunny in left direction
    public void faceLeft() {
        direction = -1;
    }

    // MODIFIES: this
    // EFFECTS: restricts Bunny within window's x-boundaries
    private void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        }
        if (posX > BFGame.WIDTH - 85) {
            posX = BFGame.WIDTH - 85;
        }
    }

    // MODIFIES: BUNNY_FILE
    // EFFECTS: saves current Bunny stats to BUNNY_FILE
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(fullness);
        printWriter.print(Reader.COMMA);
        printWriter.print(lives);
    }
}