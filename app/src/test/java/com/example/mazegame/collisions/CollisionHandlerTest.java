package com.example.mazegame.collisions;

import android.graphics.Point;
import android.graphics.Rect;

import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionHandlerTest {



    @Test
    public void testHasCollided(){
        // Rx: 595 Ry: 200 Bx: 167.70067 By: 12.530333 W: 10 H: 100
        Collided collided = CollisionHandler.calculateCollided(595, 200, 20, new Point(167, 12), 10, 100);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 295 Ry: 500 Bx: 304.88147 By: 509.8733 W: 10 H : 100
        Collided collided2 = CollisionHandler.calculateCollided(304, 509, 20, new Point(295, 500), 10, 100);
        assertEquals(false, collided.isHasCollided());
        //D/ Rx: 295 Ry: 500 Bx: 305.02164 By: 509.8733 W: 110 H : 10
        Collided collided3 = CollisionHandler.calculateCollided(305, 509, 20, new Point(295, 500), 110, 10);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: -5 Ry: 600 Bx: 305.02164 By: 510.01544 W: 10 H : 100
        Collided collided4 = CollisionHandler.calculateCollided(305, 510, 20, new Point(-5, 600), 10, 100);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 95 Ry: 600 Bx: 305.02164 By: 510.01544 W: 110 H : 10
        Collided collided5 = CollisionHandler.calculateCollided(595, 200, 20, new Point(95, 600), 110, 10);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 295 Ry: 500 Bx: 304.89856 By: 509.86264 W: 10 H : 100
        Collided collided6 = CollisionHandler.calculateCollided(304, 509, 20, new Point(295, 500), 10, 100);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 295 Ry: 500 Bx: 305.02164 By: 509.86264 W: 110 H : 10
        Collided collided7 = CollisionHandler.calculateCollided(305, 509, 20, new Point(295, 500), 110, 10);
        assertEquals(false, collided.isHasCollided());
        //D/ Rx: 595 Ry: 500 Bx: 305.02164 By: 510.01544 W: 10 H : 100
        Collided collided8 = CollisionHandler.calculateCollided(305, 510, 20, new Point(595, 500), 10, 100);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 595 Ry: 600 Bx: 305.02164 By: 510.01544 W: 10 H : 100
        Collided collided9 = CollisionHandler.calculateCollided(305, 510, 20, new Point(595, 600), 10, 100);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 295 Ry: 500 Bx: 305.02164 By: 509.8817 W: 110 H : 10
        Collided collided0 = CollisionHandler.calculateCollided(305, 509, 20, new Point(295, 500), 110, 10);
        assertEquals(false, collided.isHasCollided());
        //D/Rx: 395 Ry: 500 Bx: 305.02164 By: 510.01544 W: 110 H : 10
        Collided collided01 = CollisionHandler.calculateCollided(305, 510, 20, new Point(395, 500), 110, 10);
        assertEquals(false, collided.isHasCollided());
        Collided collided02 = CollisionHandler.calculateCollided(0, 0, 20, new Point(0, 0), 110, 10);
        assertEquals(false, collided.isHasCollided());

    }

}