package com.example.mazegame;

import com.example.mazegame.MazeCreation.Maze;
import com.example.mazegame.MazeCreation.RandomMaze;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class BallVectorTest {

    private BallVector ballVector;

    @Before
    public void createballVector(){
        ballVector = new BallVector(100, 100);
    }


    @Test
    public void testGetters(){
        assertEquals(0.0, ballVector.getRadius(), 0.0);
        assertEquals(50.0,ballVector.getXpos(), 0.0);
        assertEquals(30.0,ballVector.getYpos(), 0.0);
    }

    @Test
    public void testSetters(){
        ballVector.setRadius(30.0f);
        ballVector.setXpos(50.0f);
        ballVector.setYpos(50.0f);
        float xpos = 50.0f;
        float ypos = 50.0f;
        assertEquals(30.0f, ballVector.getRadius(), 0.0);
        assertEquals(xpos, ballVector.getXpos(), 0.0);
        assertEquals(ypos, ballVector.getYpos(), 0.0);

    }

    @Test
    public void testBallPositionCalculation(){
        //see CollisionHandlerTest
    }




}