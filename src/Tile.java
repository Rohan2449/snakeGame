package src;

import java.awt.*;

public class Tile extends Rectangle {
    private static int tileWidth = 20;
    private static int tileHeight = 20;

    public Tile(int x, int y){
        super(x,y,tileWidth,tileHeight);
    }

    public static int getTileWidth() {
        return tileWidth;
    }


    public static int getTileHeight() {
        return tileHeight;
    }


}
