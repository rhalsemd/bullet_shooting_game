package Main;

import java.awt.*;

public class HUD {

    public static float HEALTH = 100;
    private float greenValue = 255; // RGB

    private static int score = 0;
    private static int level = 1;

    public void tick(){ // 체력바의 움직임
        HEALTH = (int)Game.clamp(HEALTH, 0, 100 );
        greenValue = (int)Game.clamp(greenValue, 0, 255);

        greenValue = (int)HEALTH*2; // 체력바의 변화 값
        score++;
    }

    public void render(Graphics g){ // 체력바
        Font fnt = new Font("arial", 1, 40);
        Font fnt2 = new Font("arial", 1, 50);
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, (int)greenValue, 0));
        g.fillRect(15,15, (int)HEALTH *2, 32);
        g.setColor(Color.white);
        g.drawRect(15,15, 200, 32);
        g.setFont(fnt);
        g.drawString("Score : " + score, 20, 90); // 올라가는 점수량을 조절 가능
        if(level <20) {

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Save", 800, 50);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Load", 1000, 50);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH + 250) / 2, 10, 180, 50);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH + 660) / 2, 10, 180, 50);
        }
    }

    public void score(int score){
        this.score = score;
    }

    public int getscore(){ return score; }

    public void level(int level){ this.level = level; }

    public int getlevel(){
        return level;
    }
}
