package Main;

import java.awt.*;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;
    private int Diff;

    public SmartEnemy(float x, float y, ID id, int Diff, Handler handler){
        super(x,y,id);

        this.handler = handler;
        this.Diff = Diff;

        for(int i = 0 ; i < handler.object.size();i++){
            if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
    }

    public Rectangle getBounds(){ // 사각형을 출력
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick(){
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float)Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));

        if(Diff == 0) {
            velX = (float) ((-2.5 / distance) * diffX);
            velY = (float) ((-2.5 / distance) * diffY);
        }
        else if(Diff == 1){
            velX = (float) ((-3.5 / distance) * diffX);
            velY = (float) ((-3.5 / distance) * diffY);
        }

        // 벽에 충돌 방지 (숫자는 의미 없이 보이는데로 정했습니다.)
        if(y <= 0 || y >= Game.HEIGHT-45) velY *= -1;
        if(x <= -2 || x >= Game.WIDTH-20) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail,new Color(181,178,255), 16, 16, 0.03f, handler, false)); // life가 커지면 잔상의 길이가 짧아집니다.
    }

    public void render(Graphics g){
        g.setColor(new Color(181,178,255));
        g.fillRect((int)x,(int)y,16,16);
    }

}
