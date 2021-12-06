package Main;

import java.awt.*;

public class SpecialEnemy extends BasicEnemy { // 임의로 만들어본 적입니다.

    private Handler handler;

    public SpecialEnemy(int x, int y, ID id, int Diff, Handler handler){
        super(x,y,id, Diff, handler);
        this.handler = handler;
        if(Diff == 0) {
            velX = 9;
            velY = 2;
        }
        else if(Diff == 1){
            velX = 10;
            velY = 3;
        }
    }

    public Rectangle getBounds(){ // 사각형을 출력
        return new Rectangle((int)x, (int)y, 10, 10);
    }

    public void tick(){
        x += velX;
        y += velY;

        // 벽에 충돌 방지 (숫자는 의미 없이 보이는데로 정했습니다.)
        if(y <= 0 || y >= Game.HEIGHT-45) velY *= -1;
        if(x <= -2 || x >= Game.WIDTH-20) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(189, 189, 189), 10, 10, 0.03f, handler, false)); // life가 커지면 잔상의 길이가 짧아집니다.
    }

    public void render(Graphics g){
        g.setColor(new Color(189, 189, 189));
        g.fillRect((int)x,(int)y,10,10);
    }
}
