package model;

import persistence.Reader;
import persistence.Writer;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a Bunny Feeder game
// CREDITS: SpaceInvaders game from CPSC-210
public class BFGame {

    private static final String BUNNY_FILE = "./data/bunnies.txt";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    private static final int CARROT_FALL = 200;
    private static final int SOCK_FALL = 200;
    public static final Random RND = new Random();

    private Bunny bunny;
    private List<Carrot> carrots;
    private List<Sock> socks;
    private boolean isGameOver;
    private int fullness;
    private int lives;

    // EFFECTS: constructs new Bunny Feeder game with empty list of carrots and socks
    public BFGame() {
        carrots = new ArrayList<Carrot>();
        socks = new ArrayList<Sock>();
        setUp();
    }

    // MODIFIES: this
    // EFFECTS: clears list of carrots and socks, initialize new Bunny with default stats
    private void setUp() {
        carrots.clear();
        socks.clear();
        bunny = new Bunny(WIDTH / 2);
        isGameOver = false;
        fullness = 0;
        lives = 3;
    }

    // MODIFIES: this
    // EFFECTS: updates carrot, sock, and Bunny and checks game progress
    public void update() {
        moveCarrot();
        moveSock();
        bunny.move();

        rainItems();
        checkCarrot();
        checkSock();
        checkCaught();
        checkGameOver();
    }

    // MODIFIES: this
    // EFFECTS: moves the carrots down the screen
    private void moveCarrot() {
        for (Carrot carrot : carrots) {
            carrot.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the socks down the screen
    private void moveSock() {
        for (Sock sock : socks) {
            sock.move();
        }
    }

    // getters
    public int getFullness() {
        return fullness;
    }

    public int getLives() {
        return lives;
    }

    public Bunny getBunny() {
        return bunny;
    }

    public List<Carrot> getCarrots() {
        return carrots;
    }

    public List<Sock> getSocks() {
        return socks;
    }

    // MODIFIES: this
    // EFFECTS: produces next carrots and socks
    private void rainItems() {
        rainCarrot();
        rainSock();
    }

    // MODIFIES: this
    // EFFECTS: produces new carrot at top of screen at random
    private void rainCarrot() {
        if (RND.nextInt(CARROT_FALL) < 1) {
            Carrot c = new Carrot(RND.nextInt(BFGame.WIDTH), 0);
            carrots.add(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: produces new sock at top of screen at random
    private void rainSock() {
        if (RND.nextInt(SOCK_FALL) < 1) {
            Sock s = new Sock(RND.nextInt(BFGame.WIDTH), 0);
            socks.add(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes carrots that have traveled off bottom of screen
    private void checkCarrot() {
        List<Carrot> carrotsToRemove = new ArrayList<>();

        for (Carrot next : carrots) {
            if (next.getY() > this.WIDTH) {
                carrotsToRemove.add(next);
            }
        }

        carrots.removeAll(carrotsToRemove);
    }

    // MODIFIES: this
    // EFFECTS: removes socks that have traveled off bottom of screen
    private void checkSock() {
        List<Sock> socksToRemove = new ArrayList<>();

        for (Sock next : socks) {
            if (next.getY() > this.WIDTH) {
                socksToRemove.add(next);
            }
        }

        socks.removeAll(socksToRemove);
    }

    // MODIFIES: this
    // EFFECTS: removes any items caught by Bunny and
    // if carrot, choose between normal carrot or poisonous carrot
    // if sock, choose between normal sock or magical sock
    private void checkCaught() {
        List<Carrot> carrotsToRemove = new ArrayList<>();
        List<Sock> socksToRemove = new ArrayList<>();

        for (Carrot targetC : carrots) {
            if (targetC.caught(bunny)) {
                carrotsToRemove.add(targetC);
                chooseCarrot(targetC, bunny);
                fullness = bunny.getFullness();
                lives = bunny.getLives();
            }
        }

        for (Sock targetS : socks) {
            if (targetS.caught(bunny)) {
                socksToRemove.add(targetS);
                chooseSock(targetS, bunny);
                lives = bunny.getLives();
                fullness = bunny.getFullness();
            }
        }

        carrots.removeAll(carrotsToRemove);
        socks.removeAll(socksToRemove);
    }

    // MODIFIES: this
    // EFFECTS: if random int lands on 3 or 7, carrot is poisonous and removes a life
    // otherwise, feeds Bunny by FULLNESS_PER_CARROT
    private void chooseCarrot(GameItem targetC, Bunny bunny) {
        Random random = new Random();
        int upperLimit = 11;
        int randomInt = random.nextInt(upperLimit);
        if ((randomInt == 3) || (randomInt == 7)) {
            targetC.removeLife(bunny);
        } else {
            targetC.addFullness(bunny);
        }
    }

    // MODIFIES: this
    // EFFECTS: if random int lands on 5 or 8, magical sock gives back a life
    // otherwise, removes a life
    private void chooseSock(GameItem targetS, Bunny bunny) {
        Random random = new Random();
        int upperLimit = 11;
        int randomInt = random.nextInt(upperLimit);
        if ((randomInt == 5) || (randomInt == 8)) {
            targetS.addLife(bunny);
        } else {
            targetS.removeLife(bunny);
        }
    }

    // MODIFIES: this
    // EFFECTS: responds to user input to move Bunny, or replay/ save/ reload/ quit game
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_R && isGameOver) {
            setUp();
        } else if (keyCode == KeyEvent.VK_X) {
            saveBunny();
        } else if (keyCode == KeyEvent.VK_P) {
            loadBunny();
        } else if (keyCode == KeyEvent.VK_Q) {
            System.exit(0);
        } else {
            bunnyControl(keyCode);
        }
    }

    // MODIFIES: this
    // EFFECTS: load previous Bunny stats if player wished to reload
    // otherwise, loads new Bunny
    private void loadBunny() {
        try {
            List<Bunny> savedBunny = Reader.readStats(new File(BUNNY_FILE));
            bunny = savedBunny.get(0);
            new BFGame();
            carrots = new ArrayList<>();
            socks = new ArrayList<>();
            carrots.clear();
            socks.clear();
            fullness = this.bunny.getFullness();
            lives = this.bunny.getLives();
            this.bunny.setPosX(BFGame.WIDTH / 2);
            isGameOver = false;
        } catch (IOException e) {
            initDefaultBunny();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a default bunny
    private void initDefaultBunny() {
        new BFGame();
    }

    // EFFECTS: turns Bunny in response to key code
    private void bunnyControl(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            bunny.faceLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            bunny.faceRight();
        }
    }

    // MODIFIES: this
    // EFFECTS: if Bunny is either dead or all full, game declared over
    // and lists of carrots and socks cleared
    private void checkGameOver() {
        if (!bunny.isAlive()) {
            isGameOver = true;
        } else if (bunny.isFull()) {
            isGameOver = true;
        }

        if (isGameOver) {
            carrots.clear();
            socks.clear();
        }
    }

    // MODIFIES: BUNNY_FILE
    // EFFECTS: saves current Bunny's stats to BUNNY_FILE
    private void saveBunny() {
        try {
            Writer writer = new Writer(new File(BUNNY_FILE));
            writer.write(bunny);
            writer.close();
        } catch (FileNotFoundException e) {
            // not able to save
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: return true if game over, otherwise false
    public boolean isOver() {
        return isGameOver;
    }
}