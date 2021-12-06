package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    ArrayList<GameObject> object;
    Handler() {
        object = new ArrayList<GameObject>();
    }
    public void tick(){
        for(int i = 0 ; i < object.size(); i ++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){

        for(int i = 0 ;i < object.size() ; i++){
            try{
                GameObject tempObject = object.get(i);
                tempObject.render(g);
            }
            catch(NullPointerException e){
                continue;
            }
        }
    }

    public void clearEnemys(){ //모든 Object들을 지우고 플레이어만 다시 불러옵니다.
       for(int i = 0 ; i < object.size();i++){
           GameObject tempObject = object.get(i);
           if(tempObject.getID() == ID.Player) {
               object.clear(); // LinkedList의 모든 정보를 지우는 역할
               if(Game.gameState != Game.STATE.End)
               addObject(new Players((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
           }
       }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
