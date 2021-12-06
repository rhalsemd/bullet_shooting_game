package Main;

import java.awt.*;

public class Trail extends GameObject{ // 네모 뒤를 따르는 잔상 클래스입니다.

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;
    private boolean oval;

    //life = 0.001 - 0.1

    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler, boolean oval){
        super(x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.handler = handler;
        this.oval = oval;

    }

    public void tick() {
        if(alpha > life){
            alpha -= (life - 0.01f);
        }else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        if(oval == false) {
            g.fillRect((int) x, (int) y, width, height);
        }
        else if(oval == true){
            g.fillOval((int) x, (int) y, width, height);
        }

        g2d.setComposite(makeTransparent(1));

    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type,alpha));
    }

    public Rectangle getBounds() {
        return null;
    }
}
