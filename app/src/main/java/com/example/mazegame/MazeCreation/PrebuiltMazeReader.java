package com.example.mazegame.MazeCreation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public  class PrebuiltMazeReader  {
    private static Maze maze;


    public static Maze getMaze() {
        return maze;
    }

    public static void loadMaze(InputStream mazeInput) throws IOException {
        Scanner input = new Scanner(mazeInput);
        String line = input.nextLine();
        String[] dimensions = line.split(",");
        int width = parseInt(dimensions[0]);
        int height = parseInt(dimensions[1]);
        maze = new Maze(width,height);
        for (int i = 0; i <= height; i++) {
            line = input.nextLine();
            String[] mazeData = line.split(",");
            for (int j = 0; j <= width; j++) {
                if (parseInt(mazeData[j]) == 1 || parseInt(mazeData[j]) == 3) {
                    maze.addWall(new MazeWall(i, j, Orientation.vertical));
                }
                if (parseInt(mazeData[j]) == 2 || parseInt(mazeData[j]) == 3) {
                    maze.addWall(new MazeWall(i,j, Orientation.horizontal));
                }
            }
        }
        input.close();
    }
}
