package Main;


import java.awt.*;
import java.util.Random;

public class BossBullet extends GameObject{
    private Handler handler;
    private int Diff;
    Random r= new Random();

    public BossBullet(int x, int y, ID id, int Diff, Handler handler){
        super(x,y,id);
        this.Diff = Diff;

        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = 4;
    }

    public Rectangle getBounds(){ // 사각형을 출력
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        // 벽에 충돌 방지 (숫자는 의미 없이 보이는데로 정했습니다.)
        //if(y <= 0 || y >= Game.HEIGHT-45) velY *= -1;
        //if(x <= -2 || x >= Game.WIDTH-20) velX *= -1;
        if(y >= Game.HEIGHT) handler.removeObject(this);

        if(Diff == 0) handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(171,242,0), 20, 20, 0.12f, handler ,true)); // life가 커지면 잔상의 길이가 짧아집니다.
        else if(Diff == 1)  handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(107,153,0), 20, 20, 0.12f, handler, true));

    }

    public void render(Graphics g){
        if(Diff == 0) {
            g.setColor(new Color(171,242,0));
            g.fillOval((int) x, (int) y, 20, 20);
        }
        else if(Diff == 1){
            g.setColor(new Color(107,153,0));
            g.fillOval((int) x, (int) y, 20, 20);
        }
    }
}
