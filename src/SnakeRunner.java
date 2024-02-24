package src;
import utilities.GDV5;
import utilities.SoundDriverHo;

import java.awt.*;
import java.awt.event.KeyEvent;


public class SnakeRunner extends GDV5{
    private static String[] fileNames = {"CrunchSound.wav", "BackgroundMusic.wav"};



    private static SnakeRunner sr = new SnakeRunner();
    private static SoundDriverHo sound = new SoundDriverHo(fileNames,sr );

    private static Snake s1 = new Snake();

     private Interface inx = new Interface();
    //     0- main menu,  1- game, 2- win, 3-loss
    private static int gameStatus =0;

    private static Rectangle staminaBar = new Rectangle(634, 80, s1.getStamina() * 5/8, 20);

    public SnakeRunner(){
        Board.createBoard();


    }

    public void keyInputs(){
        if(KeysPressed[KeyEvent.VK_ENTER] && (gameStatus == 0 || gameStatus == 2 || gameStatus ==3)){
            gameStatus = 1;
        }
        if(KeysPressed[KeyEvent.VK_ESCAPE] && (gameStatus == 3 || gameStatus == 2)){
            gameStatus = 0;
            s1.resetSnake();
            s1.setLives(3);
            Snake.setLevel(1);
            Snake.setLevelTimer(60*60);
            Snake.setApplesNeeded(8);
           for(int i = Board.getFood().size()-1; i >=0; i--) {
                Board.getFood().remove(i);
           }

        }
    }

    public static void main(String[] args) {

        sr.setBackground(Color.black);
        sr.setTitle("Snake");

        sr.start();
        sr.sound.play(1);

    }

    @Override
    public void update() {
        sr.keyInputs();
        if(gameStatus == 1) {
            s1.move();

        }
        if(s1.getLives() < 1 || Snake.getLevelTimer() <= 0){
            gameStatus = 3;
        }
         if(Snake.getLevel() > 3){
             gameStatus = 2;
         }
    }

    @Override
    public void draw(Graphics2D win) {



        if(gameStatus ==0) {
            inx.mainMenu(win);
        }
        if(gameStatus ==1) {
            //Board.drawBoard(win);
            Board.drawFood(win);

            s1.drawSnake(win);

            inx.drawScoreBoard(win);
            win.setColor(Color.green);
            win.fill(staminaBar);
        }
        if(gameStatus == 2){
            inx.gameWIn(win);
        }
        if(gameStatus == 3) {

            inx.gameLoss(win);
        }
    }

    public static Rectangle getStaminaBar() {
        return staminaBar;
    }

    public static SoundDriverHo getSound() {
        return sound;
    }



    public static Snake getS1() {
        return s1;
    }



}

