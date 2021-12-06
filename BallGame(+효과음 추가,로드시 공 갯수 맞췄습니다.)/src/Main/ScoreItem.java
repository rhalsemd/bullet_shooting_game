package Main;

import java.awt.*;

public class ScoreItem extends GameObject { // 임의로 아이템을 위해서 만들었습니다.
    private Handler handler;
    private HUD hud;
    private int Diff;

    public ScoreItem(int x, int y, ID id, Handler handler, int Diff, HUD hud){
        super(x,y,id);

        this.handler = handler;
        this.hud = hud; // 점수를 가져오기 위해 사용
        this.Diff = Diff;
        exist = true;
    }

    public Rectangle getBounds(){ // 사각형을 출력
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick(){
        if(exist == false) {
            handler.removeObject(this);
            if(Diff == 0)
                hud.score(hud.getscore() + 300);
            else if(Diff == 1)
                hud.score(hud.getscore() + 1000);
        }
    }

    public void render(Graphics g){
        g.setColor(new Color(134,229,127));
        g.fillOval((int)x,(int)y, 30, 30);
    }
}
