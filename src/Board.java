package src;

import utilities.GDV5;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private static final int rows = GDV5.getMaxWindowY()/ Tile.getTileHeight();
    private static final int columns = GDV5.getMaxWindowX() / Tile.getTileWidth();
    private static final Tile[][] board = new Tile[rows][columns];
    private static final ArrayList<Tile> food = new ArrayList<>();

    public  static void createBoard(){
        int tileY = 0;
        for(int r =0; r< board.length; r++){
            int tileX = 0;
            for(int c = 0; c<board[0].length; c++ ){
                board[r][c] = new Tile(tileX, tileY);
                tileX += Tile.getTileWidth();
            }
            tileY += Tile.getTileHeight();
        }
    }

    public static int getTileX(int x){
        return (board[0][x].x);
    }
    public static int getTileY( int y){
        return (board[y][0].y);
    }


    public  static void drawBoard(Graphics2D win){
        win.setColor(Color.lightGray);

        for(Tile[] row : board){
            for(Tile tile: row){
                win.draw(tile);

            }
        }
    }

    public static  void addfood(){
        int x = (int)(Math.random()*40);
        int y = (int)(Math.random()*30);
        food.add(new Tile(x * 20, y * 20));

    }

    public static void drawFood(Graphics2D win){
        win.setColor(Color.red);
        for(Tile t : food){
            win.fill(t);
        }
    }





    public static Tile[][] getBoard() {
        return board;
    }


    public static ArrayList<Tile> getFood() {
        return food;
    }


}
