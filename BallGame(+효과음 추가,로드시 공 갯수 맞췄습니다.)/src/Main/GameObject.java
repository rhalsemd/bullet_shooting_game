package Main;

import java.awt.*;

public abstract class GameObject {

    protected float x, y; // 좌표
    protected ID id; // 적인지 플레이어 인지 구분
    protected float velX, velY; // 속도
    protected boolean exist;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){ this.y = y; }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    //임의로 아이템을 위해서 만들었습니다.
    public void setExist(boolean exist){ this.exist = exist; }
}
