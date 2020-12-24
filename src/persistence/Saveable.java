package persistence;

import java.io.PrintWriter;

// Represent data to be saved to file
// CREDITS: TellerApp repo from CPSC-210
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: write saveable to printWriter
    void save(PrintWriter printWriter);
}