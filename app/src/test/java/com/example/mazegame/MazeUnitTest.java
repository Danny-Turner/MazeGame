package com.example.mazegame;

import com.example.mazegame.MazeCreation.Maze;
import com.example.mazegame.MazeCreation.MazeWall;
import com.example.mazegame.MazeCreation.Orientation;
import com.example.mazegame.MazeCreation.PrebuiltMazeReader;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MazeUnitTest {

    @Before
    public void setup() throws IOException {
        File test = new File("src" + File.separator + "main" + File.separator +
                "assets" + File.separator + "test");
        PrebuiltMazeReader.loadMaze(new FileInputStream(test));
    }

    @Test
    public void testPrebuiltMazeLoader() {
        Maze test = PrebuiltMazeReader.getMaze();
        ArrayList<MazeWall> testWalls = test.getWalls();
        assertEquals(3,test.getHeight());
        assertEquals(3,test.getWidth());
        assertEquals(true,test.contains(new MazeWall(0,0,Orientation.horizontal)));
        assertEquals(true,test.contains(new MazeWall(0,0,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(0,1,Orientation.horizontal)));
        assertEquals(true,test.contains(new MazeWall(0,1,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(0,2,Orientation.horizontal)));
        assertEquals(false,test.contains(new MazeWall(0,2,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(0,3,Orientation.vertical)));
        assertEquals(false,test.contains(new MazeWall(1,0,Orientation.horizontal)));
        assertEquals(true,test.contains(new MazeWall(1,0,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(1,1,Orientation.horizontal)));
        assertEquals(false,test.contains(new MazeWall(1,1,Orientation.vertical)));
        assertEquals(false,test.contains(new MazeWall(1,2,Orientation.horizontal)));
        assertEquals(false,test.contains(new MazeWall(1,2,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(1,3,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(2,0,Orientation.horizontal)));
        assertEquals(true,test.contains(new MazeWall(2,0,Orientation.vertical)));
        assertEquals(false,test.contains(new MazeWall(2,1,Orientation.horizontal)));
        assertEquals(false,test.contains(new MazeWall(2,1,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(2,2,Orientation.horizontal)));
        assertEquals(false,test.contains(new MazeWall(2,2,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(2,3,Orientation.vertical)));
        assertEquals(true,test.contains(new MazeWall(3,0,Orientation.horizontal)));
        assertEquals(true,test.contains(new MazeWall(3,1,Orientation.horizontal)));
        assertEquals(false,test.contains(new MazeWall(3,2,Orientation.horizontal)));
    }


    @Test
    public void testAddwalls() {
        Maze testMaze = new Maze(10,15);
        assertEquals(0,testMaze.getWalls().size());
        testMaze.addWall(new MazeWall(2,3, Orientation.horizontal));
        testMaze.addWall(new MazeWall(1,4, Orientation.vertical));
        assertEquals(2,testMaze.getWalls().size());
    }




}