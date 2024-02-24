package src;

import utilities.GDV5;

import java.awt.*;

public class Interface {
    private static final Font fgtSS = new Font("Founders Grotesk Text", Font.BOLD, 10);
    private static final Font fgtS =new Font("Founders Grotesk Text",Font.BOLD, 25);

    private static final Font fgt =new Font("Founders Grotesk Text",Font.BOLD, 40);

    public static void drawCenteredString(String str, int desiredX,int desiredY, Graphics2D win){

        FontMetrics fm = win.getFontMetrics();
        int x = desiredX - fm.stringWidth(str) /2;
        int y = desiredY + fm.getHeight()/2 ;

        win.drawString(str, x,y);

    }
    public void mainMenu(Graphics2D pb){
        pb.setFont(fgt);
        pb.setColor(Color.white);
        drawCenteredString("FRUITY SNAKE", GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/6, pb);
        drawCenteredString("press ENTER to play", GDV5.getMaxWindowX()/2 , GDV5.getMaxWindowY()/2 + 40, pb);
        pb.setFont(fgtS);
        pb.drawString("ARROW-KEYS to move", 50, GDV5.getMaxWindowY()-100);
        pb.drawString("SHIFT to boost", 50, GDV5.getMaxWindowY()-50);

    }


    public void drawScoreBoard(Graphics2D pb){
        pb.setFont(fgtS);
        pb.setColor(Color.white);
        drawCenteredString("Lives: "+ SnakeRunner.getS1().getLives(), GDV5.getMaxWindowX() -120, 40, pb);
        drawCenteredString(""+ Snake.getLevelTimer()/60 , GDV5.getMaxWindowX()/2, 40, pb);
        pb.setColor(Color.red);
        drawCenteredString(""+ (Snake.getApplesNeeded() -SnakeRunner.getS1().getSnakeArray().size() +1) , 40, 40, pb);

        if(Snake.getTextTimer() > 0 && (Snake.getTextTimer() / 40) % 2 == 1) {
            pb.setColor(Color.YELLOW);
                drawCenteredString("Eat " + Snake.getApplesNeeded() + " Apples in 60 seconds", GDV5.getMaxWindowX() / 2, 200, pb);

        }


    }
    public void gameLoss(Graphics2D pb){
        pb.setFont(fgtS);
        pb.setColor(Color.white);
        drawCenteredString("GOOD JOB! YOU LOST", GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2, pb);
        pb.setFont(fgtS);
        drawCenteredString("ESC", GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()*3/4, pb);

    }

    public void gameWIn(Graphics2D pb){
        pb.setFont(fgtS);

        pb.setColor(Color.white);
        drawCenteredString("GOOD JOB! YOU WON", GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2, pb);
        pb.setFont(fgtSS);


        pb.setFont(fgtS);
        drawCenteredString("ESC", GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()*3/4, pb);

    }

    public static Font getFgtS() {
        return fgtS;
    }

}




