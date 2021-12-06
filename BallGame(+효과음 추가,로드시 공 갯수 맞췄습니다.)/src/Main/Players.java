package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.BufferedInputStream;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Random;

public class Players extends GameObject {
    Handler handler;
    Music hit = new Music("buttonPressedMusic.mp3", false);
    public Players(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }
    public Rectangle getBounds(){ // 사각형을 출력 Rectangle 이라는 클래스의 intersects를 쓰기 위해서 필요합니다.
        return new Rectangle((int)x-3, (int)y-3, 35, 35);
    }
    public void tick() { // 속도를 조절(방향도 포함)
        x += velX;
        y += velY;
        x = Game.clamp(x,0, Game.WIDTH-37);
        y = Game.clamp(y,0, Game.HEIGHT-60);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.05f, handler ,true)); // life가 커지면 잔상의 길이가 짧아집니다.

        collision();
    }

    private void collision(){
        for(int i = 0; i < handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.SpeicalEnemy || tempObject.getID() == ID.SmartEnemy ){ // tempobejct는 기본 적
                if(getBounds().intersects(tempObject.getBounds())){ // intersects가 교차
                    //충돌시 일어날 일을 적어주세요.
                    HUD.HEALTH -= 2;
                }
            }
            else if(tempObject.getID() == ID.EnemyBoss){
                if(getBounds().intersects(tempObject.getBounds())){ // intersects가 교차
                    //충돌시 일어날 일을 적어주세요.
                    HUD.HEALTH -= 2;
                }
            }
            else if(tempObject.getID() == ID.ScoreItem){
                if(getBounds().intersects(tempObject.getBounds())){ // intersects가 교차
                    //충돌시 일어날 일을 적어주세요.
                    tempObject.setExist(false);
                }
            }
        }
    }

    public void render(Graphics g) { // 플레이어를 paint
        g.setColor(Color.white);
        g.fillOval((int)x,(int)y,32,32);
    }
}
