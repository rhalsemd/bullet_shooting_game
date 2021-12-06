package Main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject {
    Random r = new Random();

    private Handler handler;
    private int timer = 74;
    private int timer2 = 50;
    private int Diff;

    public EnemyBoss(int x, int y, ID id, int Diff,  Handler handler){
        super(x,y,id);

        this.handler = handler;
        this.Diff = Diff;
        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds(){ // 사각형을 출력
        return new Rectangle((int)x, (int)y, 96, 96);
    }

    public void tick(){
        if(Diff == 0) {
            x += velX;
            y += velY;

            if (timer <= 0) velY = 0;
            else timer--;

            if (timer <= 0) timer2--;
            if (timer2 <= 0) {
                if (velX == 0) velX = 2;
                if (velX > 0)
                    velX += 0.01f;
                else if (velX < 0)
                    velX -= 0.01f;

                velX = Game.clamp(velX, -10, 10);
                int spawn = r.nextInt(5);
                if (spawn == 0) handler.addObject(new BossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, Diff, handler));
            }
        }
        else if(Diff == 1) {
            x += velX;
            y += velY;

            if (timer <= 0) velY = 0;
            else timer--;

            if (timer <= 0) timer2--;
            if (timer2 <= 0) {
                if (velX == 0) velX = 2;
                if (velX > 0)
                    velX += 0.01f;
                else if (velX < 0)
                    velX -= 0.01f;

                velX = Game.clamp(velX, -10, 10);
                int spawn = r.nextInt(3);
                if (spawn == 0) handler.addObject(new BossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, Diff, handler));
            }
        }
        if(x <= -2 || x >= Game.WIDTH-20-80) velX *= -1;
    }

        // 벽에 충돌 방지 (숫자는 의미 없이 보이는데로 정했습니다.)
        //if(y <= 0 || y >= Game.HEIGHT-45-48) velY *= -1;

        //handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.green, 96, 96, 0.04f, handler)); // life가 커지면 잔상의 길이가 짧아집니다.
    public void render(Graphics g){
        if(Diff == 0) {
            g.setColor(new Color(171,242,0));
            g.fillRect((int) x, (int) y, 96, 96);
        }
        else if(Diff == 1){
            g.setColor(new Color(107,153,0));
            g.fillOval((int) x, (int) y, 150, 150);
        }
    }

}
