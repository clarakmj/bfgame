package persistence;

import model.Bunny;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that reads saved Bunny stats from a file
// CREDITS: TellerApp repo from CPSC-210
public class Reader {
    public static final String COMMA = ",";

    public Reader() {
    }

    // EFFECTS: return Bunny stats parsed from file
    // throws IOException if raised during opening file
    public static List<Bunny> readStats(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as list of strings
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns list of Bunny stats,
    // each string representing previous status of Bunny
    private static List<Bunny> parseContent(List<String> fileContent) {
        List<Bunny> stats = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            stats.add(parseStats(lineComponents));
        }

        return stats;
    }

    // EFFECTS: return list of strings from line separated by COMMA
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(COMMA);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: has 2 elements, where element 0 has Bunny's fullness,
    // element 1 has Bunny's extra lives left
    // EFFECTS: returns Bunny made with previous stats
    private static Bunny parseStats(List<String> elements) {
        int fullness = Integer.parseInt(elements.get(0));
        int lives = Integer.parseInt(elements.get(1));
        return new Bunny(fullness, lives);
    }


}