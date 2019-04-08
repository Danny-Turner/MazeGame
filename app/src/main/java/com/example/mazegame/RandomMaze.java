package com.example.mazegame;


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
        for (int i = 0; i< width; i++){
            for (int j = 0; j< height; j++) {
                mazeGrid[j][i] = new RandomMazeCellData();
            }
        }
    }

    private int numberUnvisitedNeighbors(int row, int column) {
        int count = 0;
        if (row > 0 && !mazeGrid[row-1][column].isVisited()) {
            count++;
        }
        if (row < height-1 && !mazeGrid[row+1][column].isVisited()) {
            count++;
        }
        if (column > 0 && !mazeGrid[row][column-1].isVisited()) {
            count++;
        }
        if (column < width-1 && !mazeGrid[row][column+1].isVisited()) {
            count++;
        }
        return count;
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


      private void generateRandomPaths(){
        int count = 1;
        int row = 0;
        int column = 0;
        mazeGrid[0][0].setVisited(true);
        mazeStack.push(mazeGrid[0][0]);
        while (count <= width*height) {
            if (numberUnvisitedNeighbors(row,column) >0) {
                count +=1;
                int index = (int)Math.random()*unvisitedNeighbors(row,column).size();
                Direction heading = unvisitedNeighbors(row,column).get(index);
                if (heading.equals(Direction.UP)) {
                    removeUp(mazeGrid[row][column]);
                    row -=1;
                    mazeStack.push(mazeGrid[row][column]);
                } else if (heading.equals(Direction.DOWN)) {
                    row +=1;
                    removeUp(mazeGrid[row][column]);
                    mazeStack.push(mazeGrid[row][column]);
                } else if (heading.equals(Direction.LEFT)) {
                    removeLeft(mazeGrid[row][column]);
                    column -=1;
                    mazeStack.push(mazeGrid[row][column]);
                } else {
                    column +=1;
                    removeLeft(mazeGrid[row][column]);
                    mazeStack.push(mazeGrid[row][column]);
                }
            } else {
                mazeStack.pop();
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



    private void createWalls(){
        for (int i = 0; i < height; i++) {
             String[] mazeData = line.split(",");
            for (int j = 0; j < width; j++) {
                if (parseInt(mazeData[j]) == 1 || parseInt(mazeData[j]) == 3) {
                    addWall(new MazeWall(i, j, Orientation.vertical));
                }
                if (parseInt(mazeData[j]) == 2 || parseInt(mazeData[j]) == 3) {
                    addWall(new MazeWall(i,j, Orientation.horizontal));
                }
            }
        }
    }



}
