package persistence;

import java.io.*;

// A writer that writes Bunny stats to a file
// CREDITS: TellerApp repo from CPSC-210
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs new PrintWriter that writes data to file
    public Writer(File f) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(f, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: write saveable to file
    public void write(Saveable s) {
        s.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close the printWriter
    public void close() {
        printWriter.close();
    }
}