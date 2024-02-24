package src;

import java.awt.*;

public class SnakeBody extends Rectangle {
    static int snakeWidth = 20;
    static int snakeHeight = 20;
    int xCord;
    int yCord;
    int lastXCord;
    int lastYCord;


    //   Board.getTileX((Board.board[0].length - 1) / 2)
    //   Board.getTileY((Board.board.length - 1) / 2)
    public SnakeBody(int x, int y) {
        super(Board.getTileX(x), Board.getTileY(y), snakeWidth, snakeHeight);
        xCord = x;
        yCord = y;
        lastXCord= xCord;
        lastYCord = yCord;

    }

    public void setLastCords(int lastX, int LastY){
        lastXCord = lastX;
        lastYCord = LastY;
    }
}
