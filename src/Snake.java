package src;
import utilities.GDV5;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static utilities.GDV5.KeysPressed;

public class Snake extends Rectangle {



    private static int countDown = 1;
    private static int blinker =0;
    private boolean canMove = true;
    private int direction = -1; //0-up  1-right  2-down  3-left
    private int lives;
    private int stamina;
    private int staminaCounter = 300;
    private static int level =1;
    private static int levelTimer = 60 * 60;

    private static int textTimer = 5 * 60;
    private static int applesNeeded = 8;

    private ArrayList<SnakeBody> snakeArray;

    public Snake(){
        snakeArray = new ArrayList<>();
        snakeArray.add(new SnakeBody((Board.getBoard()[0].length-1) /2,( Board.getBoard().length -1 )/2));
        lives = 3;
        stamina = 240;
    }



    public void move(){

        int moveFrequency = 4;
        int fruitFrequency = 100;
        int sprintFrequency = moveFrequency /2;
        //  0=up  1=right  2=down  3=left      -1=stop
        if(canMove){


            if(KeysPressed[KeyEvent.VK_UP] && direction != 2)
                direction = 0;
            else if(KeysPressed[KeyEvent.VK_RIGHT] && direction != 3)
                direction = 1;
            else if(KeysPressed[KeyEvent.VK_DOWN] && direction != 0)
                direction = 2;
            else if(KeysPressed[KeyEvent.VK_LEFT] && direction != 1)
                direction = 3;
            if(KeysPressed[KeyEvent.VK_SHIFT] && stamina > 0 && direction != -1){
                moveFrequency = sprintFrequency;
                stamina--;
                SnakeRunner.getStaminaBar().setSize(stamina * 5/8,20);
                staminaCounter = 300;
            }

            if(staminaCounter > 0){
                staminaCounter--;
            }
            else {
                if(stamina < 240) {
                    stamina++;
                    SnakeRunner.getStaminaBar().setSize(stamina * 5/8, 20);
                }
            }



            if(countDown % moveFrequency == 0) {
                //assings new "LastCordinates" for the entire snake
                snakeArray.get(0).setLastCords(snakeArray.get(0).xCord, snakeArray.get(0).yCord);
                for(int i =1; i < snakeArray.size(); i++){
                    snakeArray.get(i).setLastCords(snakeArray.get(i).xCord, snakeArray.get(i).yCord);

                }


                //adds length to snake TEST

                if(countDown % fruitFrequency == 0 && direction != -1 && Board.getFood().size() <10){
                   Board.addfood();
                }


                if (direction == 0)
                    snakeArray.get(0).yCord--;
                else if (direction == 1)
                    snakeArray.get(0).xCord++;
                else if (direction == 2)
                    snakeArray.get(0).yCord++;
                else if (direction == 3)
                    snakeArray.get(0).xCord--;
                //checks if snake ran into itslef


                //cheks if snake is in-bounds

                if (snakeArray.get(0).xCord >= 0 && snakeArray.get(0).xCord < Board.getBoard()[0].length && snakeArray.get(0).yCord >= 0 && snakeArray.get(0).yCord < Board.getBoard().length) {


                    //moves the head of the snake

                    //moves the body of the snake
                    for(int i =1; i < snakeArray.size(); i++){
                        snakeArray.get(i).xCord = snakeArray.get(i-1).lastXCord;
                        snakeArray.get(i).yCord = snakeArray.get(i-1).lastYCord;
                    }
                     if(checkSelfCollision()){
                         canMove = false;
                         lives--;
                     }
                     for(int i = Board.getFood().size() -1; i >= 0; i-- ){
                         if(snakeArray.get(0).intersects(Board.getFood().get(i))) {

                             Board.getFood().remove(i);
                             addLength();
                             SnakeRunner.getSound().play(0);
                             if(applesNeeded + 1 - snakeArray.size() <= 0) {
                                 level++;
                                 resetSnake();
                                 levelTimer = 61 * 60;
                                 textTimer = 60 * 5;
                                 applesNeeded += applesNeeded;

                             }
                         }
                     }

                     if(canMove) {
                         snakeArray.get(0).setLocation(Board.getTileX(snakeArray.get(0).xCord), Board.getTileY(snakeArray.get(0).yCord));

                         for (int i = 1; i < snakeArray.size(); i++) {
                             snakeArray.get(i).setLocation(Board.getTileX(snakeArray.get(i).xCord), Board.getTileY(snakeArray.get(i).yCord));
                         }

                     }

                }
                else {
                    canMove = false;
                    lives--;
                }
            }
            if(direction != -1)
                countDown++;

        }
        if (!canMove) {
            blinker++;
            if(KeysPressed[KeyEvent.VK_SPACE])
                resetSnake();

        }
        moveFrequency = 8;
        if(levelTimer > 0)
            levelTimer--;
        if(textTimer > 0){
            textTimer--;
        }

    }

    public void addLength(){
        snakeArray.add(new SnakeBody(snakeArray.get(snakeArray.size()-1).lastXCord, snakeArray.get(snakeArray.size()-1).lastYCord));
    }

    public boolean checkSelfCollision(){
            for(int i =1; i < snakeArray.size(); i++){
                if(snakeArray.get(0).xCord == snakeArray.get(i).xCord && snakeArray.get(0).yCord == snakeArray.get(i).yCord){
                    return true;
                }
            }
        return false;
    }

    public void resetSnake(){
        for(int i = snakeArray.size()-1; i > 0; i--){
            snakeArray.remove(i);
        }
        canMove = true;
        stamina = 240;

        snakeArray.get(0).xCord = (Board.getBoard()[0].length-1) /2;
        snakeArray.get(0).yCord = ( Board.getBoard().length -1 )/2;
        direction = -1;
        snakeArray.get(0).setLocation(Board.getTileX(snakeArray.get(0).xCord), Board.getTileY(snakeArray.get(0).yCord));

        for (int i = 1; i < snakeArray.size(); i++) {
            snakeArray.get(i).setLocation(Board.getTileX(snakeArray.get(i).xCord), Board.getTileY(snakeArray.get(i).yCord));
        }

    }

    public void drawSnake(Graphics2D win){
        win.setColor(Color.white);
        if(!canMove){
            if((blinker/25)%2 == 1) {
                for (SnakeBody sb : snakeArray)
                    win.fill(sb);
                win.setFont(Interface.getFgtS());
                Interface.drawCenteredString("SPACE", GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()*3/4,win);
            }
        }
        else {
            for (SnakeBody sb : snakeArray)
                win.fill(sb);
        }

    }





    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getStamina() {
        return stamina;
    }


    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Snake.level = level;
    }

    public static int getLevelTimer() {
        return levelTimer;
    }

    public static void setLevelTimer(int levelTimer) {
        Snake.levelTimer = levelTimer;
    }

    public static int getTextTimer() {
        return textTimer;
    }


    public static int getApplesNeeded() {
        return applesNeeded;
    }

    public static void setApplesNeeded(int applesNeeded) {
        Snake.applesNeeded = applesNeeded;
    }

    public ArrayList<SnakeBody> getSnakeArray() {
        return snakeArray;
    }

}
