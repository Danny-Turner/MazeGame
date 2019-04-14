package com.example.mazegame;

import com.example.mazegame.MazeCreation.Maze;
import com.example.mazegame.MazeCreation.MazeWall;
import com.example.mazegame.MazeCreation.Orientation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MazeUnitTest {
    Maze testMaze = new Maze(10,15);

    @Test
    public void testAddwalls() {
        assertEquals(0,testMaze.getWalls().size());
        testMaze.addWall(new MazeWall(2,3, Orientation.horizontal));
        testMaze.addWall(new MazeWall(1,4, Orientation.vertical));
        assertEquals(2,testMaze.getWalls().size());
    }
}