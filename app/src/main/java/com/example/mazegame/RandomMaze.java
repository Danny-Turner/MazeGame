package com.example.mazegame;


import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Integer.parseInt;

enum Direction {UP,DOWN,LEFT,RIGHT}

public class RandomMaze extends Maze{
    private int width, height;
    private ArrayList<MazeWall> walls;
    private RandomMazeCellData[][] mazeGrid;
    private Stack<RandomMazeCellData> mazeStack;

    public RandomMaze(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height;
        walls = new ArrayList<>();
        mazeStack  = new Stack<RandomMazeCellData> ();
        mazeGrid = new RandomMazeCellData[height][width];
        for (int i = 0; i< width; i++){
            for (int j = 0; j< height; j++) {
                mazeGrid[j][i] = new RandomMazeCellData(j,i);
            }
        }
    }

    private ArrayList<Direction> unvisitedNeighbors(int row, int column) {
        ArrayList<Direction> directions = new ArrayList<>();
        if (row > 0 && !mazeGrid[row-1][column].isVisited()) {
            directions.add(Direction.UP);
        }
        if (row < height-1 && !mazeGrid[row+1][column].isVisited()) {
            directions.add(Direction.DOWN);
        }
        if (column > 0 && !mazeGrid[row][column-1].isVisited()) {
            directions.add(Direction.LEFT);
        }
        if (column < width-1 && !mazeGrid[row][column+1].isVisited()) {
            directions.add(Direction.RIGHT);
        }
        return directions;
    }


      public void generateRandomPaths(){
        int row = (int)(height/3);
        int column = (int) (width/3);
        mazeGrid[row][column].setVisited(true);
        mazeStack.push(mazeGrid[row][column]);
        while (!mazeStack.empty() ) {
            if (unvisitedNeighbors(row,column).size() >0) {
                int index = (int)(Math.random()*unvisitedNeighbors(row,column).size());
                Direction heading = unvisitedNeighbors(row,column).get(index);
                if (heading.equals(Direction.UP)) {
                    removeUp(mazeGrid[row][column]);
                    row -=1;
                    mazeGrid[row][column].setVisited(true);
                    mazeStack.push(mazeGrid[row][column]);
                } else if (heading.equals(Direction.DOWN)) {
                    row +=1;
                    removeUp(mazeGrid[row][column]);
                    mazeGrid[row][column].setVisited(true);
                    mazeStack.push(mazeGrid[row][column]);
                } else if (heading.equals(Direction.LEFT)) {
                    removeLeft(mazeGrid[row][column]);
                    column -=1;
                    mazeGrid[row][column].setVisited(true);
                    mazeStack.push(mazeGrid[row][column]);
                } else {
                    column +=1;
                    removeLeft(mazeGrid[row][column]);
                    mazeGrid[row][column].setVisited(true);
                    mazeStack.push(mazeGrid[row][column]);
                }
            } else {
                RandomMazeCellData current = mazeStack.pop();
                row = current.getRow();
                column = current.getColumn();
            }
        }


    }

    private void removeUp(RandomMazeCellData cell) {
        if (cell.getValue() ==3){
            cell.setValue(1);
        } else if(cell.getValue()==2) {
            cell.setValue(0);
        }
    }

    private void removeLeft(RandomMazeCellData cell) {
        if (cell.getValue() == 3){
            cell.setValue(2);
        } else if(cell.getValue()==1) {
            cell.setValue(0);
        }
    }



    public void createWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((mazeGrid[i][j].getValue()) == 1 || (mazeGrid[i][j].getValue()) == 3) {
                    addWall(new MazeWall(i, j, Orientation.vertical));
                }
                if ((mazeGrid[i][j]).getValue() == 2 || (mazeGrid[i][j].getValue()) == 3) {
                    addWall(new MazeWall(i, j, Orientation.horizontal));
                }
            }
            addWall(new MazeWall(i, width, Orientation.vertical));
        }
        for (int k = 0; k < width - 1; k++) {
            addWall(new MazeWall(height, k, Orientation.horizontal));
        }
    }



}
