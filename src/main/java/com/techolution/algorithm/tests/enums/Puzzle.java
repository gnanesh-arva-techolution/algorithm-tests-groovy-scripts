package com.techolution.algorithm.tests.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gnanesh Arva
 * @since 21 Sep 2017 at 16:28
 */
public enum Puzzle {

    BALANCED_OR_NOT("Balanced Or Not"), BUYING_SHOW_TICKETS("Buying Show Tickets"),
    CONSECUTIVE_SUM("Consecutive Sum"), FIND_THE_WINNER("Find The Winner"),
    IN_THE_FUTURE("In The Future"), LARGE_RESPONSES("Large Responses"),
    LARGEST_SUBSET_SUM("Largest Subset Sum"), PSYCHOMETRIC_TESTING("Psychometric Testing"),
    TWO_CIRCLES("Two Circles");

    String description;

    Puzzle(String description) {
        this.description = description;
    }

    public static Puzzle getPuzzle(String description) {
        Puzzle[] puzzles = values();
        for (int i = 0; i < puzzles.length; i++) {
            if (puzzles[i].description.equalsIgnoreCase(description)) {
                return puzzles[i];
            }
        }
        return null;
    }

    public static List<String> getAllPuzzleNames() {
        List<String> puzzleNames = new ArrayList<>();
        Puzzle[] puzzles = values();
        for (int i = 0; i < puzzles.length; i++) {
            puzzleNames.add(puzzles[i].getDescription());
        }
        return puzzleNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
