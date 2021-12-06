package Main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends BasicEnemy { // 임의로 만들어본 적입니다.

    private Handler handler;
    private int Diff = 0;

    Random r = new Random();

    private Color col;

    int dir = 0;

    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x,y,id, 0, handler);
        this.handler = handler;
        this.Diff = 0;



        velX = (r.nextInt(5 - -5) + 5);
        velY = (r.nextInt(5 - -5) -5);
        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds(){ // 사각형을 출력
        return new Rectangle((int)x, (int)y, 20, 20);
    }

    public void tick(){
        x += velX;
        y += velY;

        // 벽에 충돌 방지 (숫자는 의미 없이 보이는데로 정했습니다.)
        if(y <= 0 || y >= Game.HEIGHT-45) velY *= -1;
        if(x <= -2 || x >= Game.WIDTH-20) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 20, 20, 0.03f, handler ,false)); // life가 커지면 잔상의 길이가 짧아집니다.
    }

    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int)x,(int)y,20,20);
    }
}
