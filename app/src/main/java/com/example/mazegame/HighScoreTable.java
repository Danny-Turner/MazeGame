package com.example.mazegame;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScoreTable {
    private static HighScoreTable instance;
    private static ArrayList<HighScore> topScoresList;
    boolean isHighScore;

    public static HighScoreTable get() throws IOException {
        if (instance == null) {
            instance = new HighScoreTable();
        }
        return instance;
    }

    public HighScoreTable() throws IOException {
        topScoresList = new ArrayList<>();
        addNewScore(new HighScore("Dr. Ferrer",10000));
        addNewScore(new HighScore("Rader",15000));
    }

    public ArrayList<HighScore> getTopScores() {
        return topScoresList;
    }

    public static void addNewScore(HighScore hs){
        topScoresList.add(hs);
    }

    public void sortScores(){
        Collections.sort(topScoresList, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore o1, HighScore o2) {
                return Long.compare(o1.getTime(), o2.getTime());
            }
        });
    }

    public boolean isHighScore(long newTime) {
        for (int i=0; i<10; i++) {
            if (newTime < topScoresList.get(i).getTime()) {
                isHighScore = true;
            }
        }
        return isHighScore;
    }


    public static void loadHighScores(InputStream scoreInput) throws IOException {
        Scanner input = new Scanner(scoreInput);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] scoreData = line.split(",");
            topScoresList.add(new HighScore(scoreData[0],Long.parseLong(scoreData[1])));
        }
        input.close();
    }


}
