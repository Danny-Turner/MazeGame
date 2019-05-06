package com.example.mazegame;

import com.example.mazegame.MazeCreation.PrebuiltMazeReader;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;

public class HighScoreTests {
    HighScoreTable table;
    HighScore score;


    @Before
    public void setup() throws IOException {
        table = HighScoreTable.get();
        score = new HighScore("Tester", 2000);
    }

    @Test
    public void addScoreTest(){
        int before = table.getTopScores().size();
        table.addNewScore(score);
        assertEquals(before+1,table.getTopScores().size());
    }

    @Test
    public void isHighScoreTest(){
        for (int i = 0; i< 9; i++){
            table.addNewScore(score);
        }
        assertEquals(true,table.isHighScore(50));
        assertEquals(false,table.isHighScore(5555555));
    }

    @Test
    public void  testSortScores() {
        HighScore great = new HighScore("Awesome1", 1);
        table.addNewScore(great);
        table.sortScores();
        assertEquals("Awesome1",table.getTopScores().get(0).getUsername());
    }



}
